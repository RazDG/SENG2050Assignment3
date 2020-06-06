package mvc;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/PeerEvaluationOfProject"})
public class PeerEvaluationOfProject extends HttpServlet
{
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    HttpSession session = request.getSession();
    String userToEvaluate = request.getParameter("selectedStudent");
    String userComment = request.getParameter("comment");
    PeerEvaluationModel currentComment = new PeerEvaluationModel(userComment);

    Member currentMember = (Member) session.getAttribute("currentUser");
    String currentUsername = currentMember.getUsername();

    //Gets the current project
    if (session.getAttribute("currentProject") == null)
    {
      response.sendRedirect("homePage.jsp");
    }
    ProjectModel currentProject = (ProjectModel) session.getAttribute("currentProject");
    String projname = currentProject.getProjectName();

    //Insert data to tbEvaluate table containing the current project name, the selected user's name and comments
    try {
      Connection conn = ConnectDB.getConnection();
      String sqlUpdate = "INSERT INTO tbEvaluate VALUES ('"+projname+"', '"+userToEvaluate+"', '"+currentUsername+"', '"+userComment+"')";
      PreparedStatement ps = conn.prepareStatement(sqlUpdate);
      ps.executeUpdate();

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
