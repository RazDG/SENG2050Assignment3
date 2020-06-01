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
      response.sendRedirect("LogIn.jsp");
    }

    //Gets the current project
    String projname = request.getParameter("projectSelect");
    ProjectModel currentProject = new ProjectModel(projname);
    session.setAttribute("currentProject", currentProject);

    //Get a list of the project Group Members (From DB)
    ArrayList<String> projectGroupMembers = new ArrayList<String>();
    try {
      Connection conn = ConnectDB.getConnection();
      String sqlQuery = "SELECT * FROM tblGroupProjectUsers WHERE projectname = '"+projname+"'";
      Statement s = conn.createStatement();
      ResultSet rs = s.executeQuery(sqlQuery);
      //Data retrieved from DB inserted into String array
      while (rs.next())
      {
        projectGroupMembers.add(rs.getString("username"));
      }
      session.setAttribute("projectGroupMembers", projectGroupMembers);
    }
    catch (SQLException e){
      //If SQL fails
      session.setAttribute("projectGroupMembers", projectGroupMembers);
    }
    //ArrayList<String> testLIST = (ArrayList<String>) session.getAttribute("projectGroupMembers");

    response.sendRedirect("projectMenu.jsp");
  }
}
