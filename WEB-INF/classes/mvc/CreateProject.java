package mvc;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/createNewProject"})
public class CreateProject extends HttpServlet
{
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    HttpSession session = request.getSession();

    //If session variable currentUser is null, return user to login page
    if (session.getAttribute("currentUser") == null)
    {
      response.sendRedirect("LogIn.jsp");
    }

    //Get current user and the username
    Member currentUser = (Member) session.getAttribute("currentUser");
    String currentUsername = currentUser.getUsername();

    //Gets the project name
    String projname = request.getParameter("newProjectName");

    try {
      //Checks if project already exists
      Connection conn = ConnectDB.getConnection();
      String sqlQuery = "SELECT COUNT(*) AS Count FROM tblGroupProject WHERE projectname = '"+projname+"'";
      Statement s = conn.createStatement();
      ResultSet rs = s.executeQuery(sqlQuery);
      boolean projectExists = false;
      while (rs.next())
      {
        if (rs.getInt("Count") > 0) projectExists = true;
      }
      if (projectExists)
      {
        rs.close();
        s.close();
        conn.close();
        response.sendRedirect("ErrorCreatingProject.jsp");
      }
      else
      {
        //Inserts project info into DB
        String sqlUpdate = "INSERT INTO tblGroupProject VALUES ('"+projname+"');";
        sqlUpdate+= "INSERT INTO tblGroupProjectUsers VALUES ('"+projname+"', '"+currentUsername+"');";
        PreparedStatement ps = conn.prepareStatement(sqlUpdate);
        ps.executeUpdate();

        //Cleanup
        rs.close();
        s.close();
        ps.close();
        conn.close();

        //Create and update session variables
        currentUser.setProject(projname);
        ProjectModel currentProject = new ProjectModel(projname);
        currentProject.setGroupMembers(currentUsername);
        session.setAttribute("currentUser", currentUser);
        session.setAttribute("currentProject", currentProject);

        response.sendRedirect("projectMenu.jsp");
      }
    }
    catch (SQLException e) {
      response.sendRedirect("ErrorCreatingProject.jsp");
    }
  }
}
