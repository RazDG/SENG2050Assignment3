package mvc;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/loadTasksMilestonesMenu"})
public class loadTasksMilestonesMenu extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    HttpSession session = request.getSession();

    if (session.getAttribute("currentUser") == null)
    {
      response.sendRedirect("index.jsp");
    }

    ProjectModel currentProject = (ProjectModel) session.getAttribute("currentProject");
    String projname = currentProject.getProjectName();

    ArrayList<TaskModel> tasks = new ArrayList<TaskModel>();
    ArrayList<MilestoneModel> milestones = new ArrayList<MilestoneModel>();

    //Get task data and milestone data from DB, add to Arraylists
    try {
      Connection conn = ConnectDB.getConnection();
      Statement s = conn.createStatement();
    }
    catch(SQLException e){

    }
  }
}
