package mvc;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/finaliseAddUserToProject"})
public class FinaliseAddUserToProject extends HttpServlet
{
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    HttpSession session = request.getSession();
    String usernameToAdd = request.getParameter("selectedStudent");

    //Gets the current project
    if (session.getAttribute("currentProject") == null)
    {
      response.sendRedirect("homePage.jsp");
    }
    ProjectModel currentProject = (ProjectModel) session.getAttribute("currentProject");
    String projname = currentProject.getProjectName();

    //Insert an entry in the tblGroupProjectUsers table containing the current project name and the selected user's name
    try {
      Connection conn = ConnectDB.getConnection();
      String sqlUpdate = "INSERT INTO tblGroupProjectUsers VALUES ('"+projname+"', '"+usernameToAdd+"')";
      PreparedStatement ps = conn.prepareStatement(sqlUpdate);
      ps.executeUpdate();

      //Add the selected user to the current project's group members list
      currentProject.setGroupMembers(usernameToAdd);

      //Update the currentProject session variable with the updated ProjectModel
      session.setAttribute("currentProject", currentProject);

      //Cleanup
      ps.close();
      conn.close();

      //Redirect user to the Project Menu page
      response.sendRedirect("projectMenu.jsp");
    }
    catch(SQLException e){
      //If there is an error, redirect user to the Project Menu page
      //No changes will be made to the currentProject session variable
      response.sendRedirect("projectMenu.jsp");
    }

  }
}
