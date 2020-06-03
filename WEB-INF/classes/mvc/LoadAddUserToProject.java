package mvc;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/addUserToProject"})
public class LoadAddUserToProject extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
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

    //Gets the current project
    if (session.getAttribute("currentProject") == null)
    {
      response.sendRedirect("homePage.jsp");
    }
    ProjectModel currentProject = (ProjectModel) session.getAttribute("currentProject");
    String projname = currentProject.getProjectName();

    //Create a List of available users that can be added to the project
    ArrayList<String> availableUsers = new ArrayList<String>();

    //Get list of available users from DB and add them to the list
    try {
      Connection conn = ConnectDB.getConnection();
      String sqlQuery = "SELECT * FROM tblUser WHERE username NOT IN (SELECT username FROM tblGroupProjectUsers WHERE projectname = '"+projname+"')";
      sqlQuery += " AND usertype = 'Student'";
      Statement s = conn.createStatement();
      ResultSet rs = s.executeQuery(sqlQuery);
      while (rs.next())
      {
        availableUsers.add(rs.getString("username"));
      }

      //Add the list of students to a session variable called availableUsers
      session.setAttribute("availableUsers", availableUsers);

      //Cleanup
      rs.close();
      s.close();
      conn.close();

      //Redirect user to the addUserToProject page
      response.sendRedirect("addUserToProject.jsp");
    }
    catch(SQLException e){
      //Add the list to a session variable called availableUsers
      session.setAttribute("availableUsers", availableUsers);

      //Redirect user to the addUserToProject page
      response.sendRedirect("addUserToProject.jsp");
    }

  }
}
