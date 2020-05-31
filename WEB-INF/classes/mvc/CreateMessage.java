package mvc;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/message"})
public class CreateMessage extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		String title = request.getParameter("title");
		String email = request.getParameter("email");
		String message = request.getParameter("message");
		try
		{
			//If title exist, redirect to newMessage.jsp
			if (checkMessageExists(title)) response.sendRedirect("newMessage.jsp");
			else
			{
				//MessageInfo handles message's data
				MessageInfo messageInfo = new MessageInfo(title, email, message);
				session.setAttribute("currentUser", messageInfo);

				Connection conn = ConnectDB.getConnection();
				String sqlQuery = "INSERT INTO tbMessage VALUES ('"+title+"', '"+email+"', '"+message+"')";
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery(sqlQuery);
				while (rs.next()){}
				//Cleanup
				s.close();
				rs.close();
				conn.close();
				//response.sendRedirect("arrangeAppointment.jsp");
			}
		}
		catch(SQLException e)
		{
			//If SQL fails, redirect to error.jsp
			//response.sendRedirect("error.jsp");
			response.sendRedirect("arrangeAppointment.jsp");
		}
	}

	//Method checks if topic exists in the database
	public boolean checkMessageExists(String titleName) throws SQLException
	{
		boolean messageExists = false;

		//Establish DB connection using ConnectDB class
		Connection conn = ConnectDB.getConnection();
		String sqlQuery = "SELECT COUNT(*) AS Count FROM tbMessage WHERE topic = '"+titleName+"'";
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery(sqlQuery);
		while (rs.next())
		{
			if (rs.getInt("Count") > 0)
			{
				messageExists = true;
			}
		}

		//Cleanup
		s.close();
		rs.close();
		conn.close();
		return messageExists;
	}
}
