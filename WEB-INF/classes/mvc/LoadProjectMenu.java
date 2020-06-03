package mvc;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/loadProjectMenu"})
public class LoadProjectMenu extends HttpServlet
{
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    HttpSession session = request.getSession();

    //If session variable currentUser is null, return user to login page
    if (session.getAttribute("currentUser") == null)
    {
      response.sendRedirect("index.jsp");
    }

    //Gets the current project
    String projname = request.getParameter("projectSelect");
    ProjectModel currentProject = new ProjectModel(projname);

    //Get a list of the project Group Members (From DB)
    try {
      Connection conn = ConnectDB.getConnection();
      String sqlQuery = "SELECT * FROM tblGroupProjectUsers WHERE projectname = '"+projname+"'";
      Statement s = conn.createStatement();
      ResultSet rs = s.executeQuery(sqlQuery);
      //Data retrieved from DB inserted into String array
      while (rs.next())
      {
        currentProject.setGroupMembers(rs.getString("username"));
      }
      session.setAttribute("currentProject", currentProject);
    }
    catch (SQLException e){
      //If SQL fails
      session.setAttribute("currentProject", currentProject);
    }

    response.sendRedirect("projectMenu.jsp");
  }
}
