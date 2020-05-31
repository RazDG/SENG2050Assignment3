package mvc;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/milestone"})
public class CreateMilestone extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		String taskName = request.getParameter("taskName");
		String startDate = request.getParameter("startDate");
		String dueDate = request.getParameter("dueDate");
    String submissionDate = request.getParameter("submissionDate");
		try
		{
			//If taskName exist, redirect to newMilestone.jsp
			if (checkMilestoneExists(taskName)) response.sendRedirect("newMilestone.jsp");
			else
			{
				//MilestoneInfo handles milestone's data
				MilestoneInfo milestoneInfo = new MilestoneInfo(taskName, startDate, dueDate, submissionDate);
				session.setAttribute("currentUser", milestoneInfo);

				Connection conn = ConnectDB.getConnection();
				String sqlQuery = "INSERT INTO tbMilestone VALUES ('"+taskName+"', '"+startDate+"', '"+dueDate+"', '"+submissionDate+"')";
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery(sqlQuery);
				while (rs.next()){}
				//Cleanup
				s.close();
				rs.close();
				conn.close();
			}
		}
		catch(SQLException e)
		{
			response.sendRedirect("setMilestones.jsp");
		}
	}

	//Method checks if taskName exists in the database
	public boolean checkMilestoneExists(String name) throws SQLException
	{
		boolean milestoneExists = false;

		//Establish DB connection using ConnectDB class
		Connection conn = ConnectDB.getConnection();
		String sqlQuery = "SELECT COUNT(*) AS Count FROM tbMilestone WHERE taskName = '"+name+"'";
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery(sqlQuery);
		while (rs.next())
		{
			if (rs.getInt("Count") > 0)
			{
				milestoneExists = true;
			}
		}

		//Cleanup
		s.close();
		rs.close();
		conn.close();
		return milestoneExists;
	}
}
