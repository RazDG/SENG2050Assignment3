package mvc;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/createMilestone"})
public class CreateMilestone extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();

		//Check if user is signed in
		if (session.getAttribute("currentUser") == null)
    {
      response.sendRedirect("index.jsp");
    }

		//Get form values
		String milestoneName = request.getParameter("milestone");
		String dateYear = request.getParameter("dateYear");
		String dateMonth = request.getParameter("dateMonth");
		String dateDay = request.getParameter("dateDay");
		String dueDate = dateYear+"-"+dateMonth+"-"+dateDay;

		//Get current project
		ProjectModel currentProject = (ProjectModel) session.getAttribute("currentProject");
		String projname = currentProject.getProjectName();

		@SuppressWarnings("unchecked")
		ArrayList<MilestoneModel> milestones = (ArrayList<MilestoneModel>) session.getAttribute("currentMilestones");

		//Insert Data into database
		try
		{
			//Checks if milestone already exists
			Connection conn = ConnectDB.getConnection();
			String sqlQuery = "SELECT COUNT(*) AS Count FROM tblGroupProjectMilestones WHERE projectname = '"+projname+"' AND milestone='"+milestoneName+"'";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlQuery);
			boolean milestoneExists = false;
			while (rs.next())
			{
				if (rs.getInt("Count") > 0) milestoneExists = true;
			}
			if (milestoneExists)
			{
				rs.close();
				s.close();
				conn.close();
				response.sendRedirect("tasksAndMilestonesMenu.jsp");
			}
			else
			{
				String sqlUpdate = "INSERT INTO tblGroupProjectMilestones VALUES ('"+projname+"', '"+milestoneName+"', '"+dueDate+"', 0)";
				PreparedStatement ps = conn.prepareStatement(sqlUpdate);
				ps.executeUpdate();

				//Cleanup
				rs.close();
				s.close();
				ps.close();
				conn.close();

				//Update session variable
				MilestoneModel currentMilestone = new MilestoneModel(projname, milestoneName);
				currentMilestone.setDueDate(dueDate);
				milestones.add(currentMilestone);
				session.setAttribute("currentMilestones", milestones);

				response.sendRedirect("tasksAndMilestonesMenu.jsp");
			}
		}
		catch(SQLException e)
		{
			response.sendRedirect("tasksAndMilestonesMenu.jsp");
		}
	}
}
