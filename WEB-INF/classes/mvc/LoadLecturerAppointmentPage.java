package mvc;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/loadLectureAppointmentPage"})
public class LoadLecturerAppointmentPage extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();

    //Get current user and username
    Member currentUser = (Member) session.getAttribute("currentUser");
    String currentUsername = currentUser.getUsername();

    //Create lists to store confirmed and pending appointments
    ArrayList<AppointmentModel> confirmedAppointments = new ArrayList<AppointmentModel>();
    ArrayList<AppointmentModel> pendingAppointments = new ArrayList<AppointmentModel>();

    //Get appointment info from database
    try
    {
      Connection conn = ConnectDB.getConnection();
      String sqlQuery = "SELECT * FROM tblAppointments WHERE userSender = '"+currentUsername+"' OR userReceiver = '"+currentUsername+"'";
      Statement s = conn.createStatement();
      ResultSet rs = s.executeQuery(sqlQuery);
      AppointmentModel currentAppointment = new AppointmentModel();
      //Organise info into appointment arrays
      while(rs.next())
      {
        //If the user sent the appointment request and it was accepted by recipient
        if (rs.getString("senderUser").equalsIgnoreCase(currentUsername))
        {
          if (rs.getBoolean("isAccepted") == true)
          {
            currentAppointment = new AppointmentModel(rs.getString("userSender"), rs.getString("userReceiver"), rs.getString("appdate"), rs.getString("apptime"));
            currentAppointment.setAccepted(true);
            confirmedAppointments.add(currentAppointment);
          }
        }
        //If the user recieved the request
        if (rs.getString("userReceiver").equalsIgnoreCase(currentUsername))
        {
          //If the user accepted the request
          if (rs.getBoolean("isAccepted") == true)
          {
            currentAppointment = new AppointmentModel(rs.getString("userSender"), rs.getString("userReceiver"), rs.getString("appdate"), rs.getString("apptime"));
            currentAppointment.setAccepted(true);
            confirmedAppointments.add(currentAppointment);
          }
          //If the user has not yet accepted the request
          else {
            currentAppointment = new AppointmentModel(rs.getString("userSender"), rs.getString("userReceiver"), rs.getString("appdate"), rs.getString("apptime"));
            currentAppointment.setAccepted(false);
            pendingAppointments.add(currentAppointment);
          }
        }
      }

      //Cleanup
      rs.close();
      s.close();
      conn.close();

      //Add the Appointment lists to session variables
      session.setAttribute("confirmedAppointments", confirmedAppointments);
      session.setAttribute("pendingAppointments", pendingAppointments);

      //Redirect user to LecturerAppointmentPage
      response.sendRedirect("LecturerAppointmentPage.jsp");
    }
    catch(SQLException e)
    {
      response.sendRedirect("lecturerHomePage.jsp");
    }
	}
}
