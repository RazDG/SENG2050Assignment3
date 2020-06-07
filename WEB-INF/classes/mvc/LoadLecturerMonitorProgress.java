package mvc;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/loadLecturerMonitorProjectProgress"})
public class LoadLecturerMonitorProgress extends HttpServlet
{
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    HttpSession session = request.getSession();

    if (session.getAttribute("currentUser") == null)
    {
      response.sendRedirect("index.jsp");
    }

    String projname = request.getParameter("project");
    ProjectModel currentProject = new ProjectModel(projname);

    ArrayList<TaskModel> tasks = new ArrayList<TaskModel>();
    ArrayList<MilestoneModel> milestones = new ArrayList<MilestoneModel>();

    //Get task data and milestone data from DB, add to Arraylists
    try {
      Connection conn = ConnectDB.getConnection();
      Statement s = conn.createStatement();
      //Query database for task data
      String sqlQuery = "SELECT * FROM tblGroupProjectTasks WHERE projectname = '"+projname+"';";
      ResultSet rs = s.executeQuery(sqlQuery);
      while (rs.next())
      {
        String taskname = rs.getString("taskname");
        String assigneduser = rs.getString("assigneduser");
        String startDate = rs.getString("startDate");
        String dueDate = rs.getString("dueDate");
        TaskModel task = new TaskModel(projname, taskname);
        if (assigneduser != null) task.setAssigneduser(assigneduser);
        if (startDate != null) task.setStartDate(startDate);
        if (dueDate != null) task.setDueDate(dueDate);
        tasks.add(task);
      }

      //Query database for milestone data
      sqlQuery = "SELECT * FROM tblGroupProjectMilestones WHERE projectname = '"+projname+"';";
      rs = s.executeQuery(sqlQuery);
      while (rs.next())
      {
        String milestonename = rs.getString("milestone");
        String dueDate = rs.getString("dueDate");
        boolean isComplete = rs.getBoolean("isComplete");
        MilestoneModel milestone = new MilestoneModel(projname, milestonename);
        if (dueDate != null) milestone.setDueDate(dueDate);
        milestone.setIsComplete(isComplete);
        milestones.add(milestone);
      }

      //Add lists of tasks and milestones to session variables
      session.setAttribute("currentTasks", tasks);
      session.setAttribute("currentMilestones", milestones);
      session.setAttribute("currentProject", currentProject);

      //Cleanup
      rs.close();
      s.close();
      conn.close();

      //Redirect to monitorProjectProgress page
      response.sendRedirect("lecturerMonitorProjectProgress.jsp");
    }
    catch(SQLException e){
      response.sendRedirect("lecturerHomePage.jsp");
    }
  }
}
