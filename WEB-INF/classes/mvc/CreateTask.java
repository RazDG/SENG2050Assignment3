package mvc;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/createTask"})
public class CreateTask extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();




		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		try
		{
			//If user doesn't exist, redirect to LogIn.jsp
			if (!checkUserExists(userName, password)) response.sendRedirect("index.jsp");
			else
			{
				//Collect additional user information
				Connection conn = ConnectDB.getConnection();
				String sqlQuery = "SELECT * FROM tblUser WHERE username = '"+userName+"' AND password = '"+password+"'";
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery(sqlQuery);
				String type = "";
				while (rs.next())
				{
					type = rs.getString("usertype");
				}
				//Member handles user data
				Member member = new Member(userName, password, type);

				//Get the projects user is apart of
				sqlQuery = "SELECT * FROM tblGroupProjectUsers WHERE username = '"+userName+"'";
				s = conn.createStatement();
				rs = s.executeQuery(sqlQuery);
				//Data retrieved from DB inserted into Member
				while (rs.next())
				{
					member.setProject(rs.getString("projectname"));
				}
				//Cleanup
				s.close();
				rs.close();
				conn.close();

				//Member added to session variable
				session.setAttribute("currentUser", member);

				//If the user is a student, user is redirected to the student Home page
				if (type.equalsIgnoreCase("Student")) response.sendRedirect("homePage.jsp");
				//If the user is a lecturer, they are sent to the lecturer Home page
				else response.sendRedirect("lecturerHomePage");
			}
		}
		catch(SQLException e)
		{
			//If SQL fails, redirect to error.jsp
			response.sendRedirect("error.jsp");
		}
	}


}
