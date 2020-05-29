package mvc;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/login"})
public class LoadUser extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		try
		{
			//If user doesn't exist, redirect to LogIn.jsp
			if (!checkUserExists(userName)) response.sendRedirect("LogIn.jsp");
			else
			{
				//Member handles user data
				Member member = new Member(userName, password);
				session.setAttribute("currentUser", member);
				response.sendRedirect("homePage.jsp");
			}
		}
		catch(SQLException e)
		{
			//If SQL fails, redirect to error.jsp
			response.sendRedirect("error.jsp");
		}
	}

	//Method checks if User exists in the database
	public boolean checkUserExists(String name) throws SQLException
	{
		boolean userExists = false;

		//Establish DB connection using ConnectDB class
		Connection conn = ConnectDB.getConnection();
		String sqlQuery = "SELECT COUNT(*) AS Count FROM tblUser WHERE username = '"+name+"'";
		//String sqlQuery = "INSERT INTO tblUser (username) VALUES ('"+name+"')";
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery(sqlQuery);
		while (rs.next())
		{
			if (rs.getInt("Count") > 0)
			{
				userExists = true;
			}
		}

		//Cleanup
		s.close();
		rs.close();
		conn.close();
		return userExists;
	}
}
