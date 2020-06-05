package mvc;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/editTaskStartDate"})
public class EditTaskStartDate extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();

    ArrayList<TaskModel> tasks = (ArrayList<TaskModel>) session.getAttribute("currentTasks");

    //Get task and project
    TaskModel currentTask = (TaskModel) session.getAttribute("currentTask");
    String taskName = currentTask.getTaskname();

    ProjectModel currentProject = (ProjectModel) session.getAttribute("currentProject");
    String projname = currentProject.getProjectName();

    String value = "";
    value += request.getParameter("startDateYear");
    value += "-";
    value += request.getParameter("startDateMonth");
    value += "-";
    value += request.getParameter("startDateDay");

    //Update the Database
    try{
      Connection conn = ConnectDB.getConnection();
      String sqlUpdate = "";
      sqlUpdate = "UPDATE tblGroupProjectTasks SET startDate = '"+value+"' WHERE projectname = '"+projname+"' AND taskname = '"+taskName+"'";
      PreparedStatement ps = conn.prepareStatement(sqlUpdate);
      ps.executeUpdate();

      //Cleanup
      ps.close();
      conn.close();

      //Update session variable
      for (int i = 0; i < tasks.size(); i++)
      {
        if (tasks.get(i).getTaskname().equals(taskName))
        {
          tasks.get(i).setStartDate(value);
        }
      }
      session.setAttribute("currentTasks", tasks);

      //Redirect to tasksAndMilestonesMenu
      response.sendRedirect("tasksAndMilestonesMenu.jsp");
    }
    catch(SQLException e)
    {
      response.sendRedirect("editTask.jsp");
    }
  }
}
