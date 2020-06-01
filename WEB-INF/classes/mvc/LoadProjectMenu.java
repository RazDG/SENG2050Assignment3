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

    //The currentUser session variable is set to a local variable
    Member currentUser = (Member) session.getAttribute("currentUser");

    
  }
}
