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

    //Get task list
    @SuppressWarnings("unchecked")
    ArrayList<TaskModel> tasks = (ArrayList<TaskModel>) session.getAttribute("currentTasks");

    //Get form information
    String taskName = request.getParameter("taskName");
    String assignedUser = request.getParameter("responsibleUser");
    String startDate = "";
    startDate += request.getParameter("startDateYear");
    startDate += "-";
    startDate += request.getParameter("startDateMonth");
    startDate += "-";
    startDate += request.getParameter("startDateDay");
    String dueDate = "";
    dueDate += request.getParameter("dueDateYear");
    dueDate += "-";
    dueDate += request.getParameter("dueDateMonth");
    dueDate += "-";
    dueDate += request.getParameter("dueDateDay");
    //Get current project
    ProjectModel currentProject = (ProjectModel) session.getAttribute("currentProject");
    String projname = currentProject.getProjectName();

    //Insert data into DB
    try {
      Connection conn = ConnectDB.getConnection();

      //Check if task already exists
      String sqlQuery = "SELECT COUNT(*) AS Count FROM tblGroupProjectTasks WHERE projectname = '"+projname+"' AND taskname = '"+taskName+"';";
      Statement s = conn.createStatement();
      ResultSet rs = s.executeQuery(sqlQuery);
      boolean taskExists = false;
      while (rs.next())
      {
        if (rs.getInt("Count") > 0) taskExists = true;
      }

      if (taskExists)
      {
        response.sendRedirect("tasksAndMilestonesMenu.jsp");
        return;
      }
      String sqlUpdate = "INSERT INTO tblGroupProjectTasks VALUES ('"+projname+"', '"+taskName+"', '"+assignedUser+"', '"+startDate+"', '"+dueDate+"')";
      PreparedStatement ps = conn.prepareStatement(sqlUpdate);
      ps.executeUpdate();

      //Cleanup
      ps.close();
      conn.close();

      //Add task to current project's task list
      TaskModel currentTask = new TaskModel(projname, taskName);
      currentTask.setAssigneduser(assignedUser);
      currentTask.setStartDate(startDate);
      currentTask.setDueDate(dueDate);
      tasks.add(currentTask);
      session.setAttribute("currentTasks", tasks);

      //Redirect user back to tasksAndMilestonesMenu
      response.sendRedirect("tasksAndMilestonesMenu.jsp");
    }
    catch (SQLException e){
      response.sendRedirect("tasksAndMilestonesMenu.jsp");
    }
	}
}
