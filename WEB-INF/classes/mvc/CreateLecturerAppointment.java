package mvc;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/createLecturerAppointment"})
public class CreateLecturerAppointment extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();

    //Get current user and username
    Member currentUser = (Member) session.getAttribute("currentUser");
    String currentUsername = currentUser.getUsername();

    //Get appointment Details
    String userRecipient = request.getParameter("userSelect");
    String appdate = "";
    appdate += request.getParameter("dateYear");
    appdate += "-";
    appdate += request.getParameter("dateMonth");
    appdate += "-";
    appdate += request.getParameter("dateDay");
    String time = "";
    time += request.getParameter("time");
    time += request.getParameter("amPm");

    //Add data to the database
    try {
      Connection conn = ConnectDB.getConnection();
      String sqlUpdate = "INSERT INTO tblAppointments (userSender, userReceiver, appdate, apptime, isAccepted) VALUES ('"+currentUsername+"', '"+userRecipient+"', '"+appdate+"', '"+time+"', 0)";
      PreparedStatement ps = conn.prepareStatement(sqlUpdate);
      ps.executeUpdate();

      //Cleanup
      ps.close();
      conn.close();

      //Send user back to appointment page
      response.sendRedirect("lecturerAppointmentPage.jsp");
    }
    catch(SQLException e)
    {
      response.sendRedirect("lecturerAppointmentError.jsp");
    }
  }
}
