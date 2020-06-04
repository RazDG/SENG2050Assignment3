package mvc;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/updateMilestone"})
public class UpdateMilestone extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    HttpSession session = request.getSession();

    //If session variable currentUser is null, return user to login page
    if (session.getAttribute("currentUser") == null)
    {
      response.sendRedirect("index.jsp");
    }

    //Get project
    ProjectModel currentProject = (ProjectModel) session.getAttribute("currentProject");
    String projname = currentProject.getProjectName();

    String milestoneName = request.getParameter("name");
    String function = request.getParameter("function");

    //Get list of milestones from session variable
    ArrayList<MilestoneModel> milestones = (ArrayList<MilestoneModel>) session.getAttribute("currentMilestones");
    //Get the index of the specific milestone in the list
    int index = 0;
    for (int i = 0; i < milestones.size(); i++)
    {
      if (milestoneName.equalsIgnoreCase(milestones.get(i).getMilestone())) index = i;
    }

    //Update the database
    try{
      Connection conn = ConnectDB.getConnection();
      String sqlUpdate = "";
      if (function.equalsIgnoreCase("complete"))
      {
        sqlUpdate = "UPDATE tblGroupProjectMilestones SET isComplete = 1 WHERE milestone = '"+milestoneName+"';";
      }
      else {
        sqlUpdate = "DELETE FROM tblGroupProjectMilestones WHERE milestone = '"+milestoneName+"';";
      }
      PreparedStatement ps = conn.prepareStatement(sqlUpdate);
      ps.executeUpdate();

      //Cleanup
      ps.close();
      conn.close();

      //Update session variable
      if (function.equalsIgnoreCase("complete")) milestones.get(index).setIsComplete(true);
      else milestones.remove(index);
      session.setAttribute("currentMilestones", milestones);

      //Redirect user to tasksAndMilestonesMenu
      response.sendRedirect("tasksAndMilestonesMenu.jsp");
    }
    catch(SQLException e){
      response.sendRedirect("tasksAndMilestonesMenu.jsp");
    }
  }
}
