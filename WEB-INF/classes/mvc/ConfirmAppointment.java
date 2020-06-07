package mvc;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.*;
import java.sql.*;
import javax.naming.*;

@WebServlet(urlPatterns = {"/confirmAppointment"})
public class ConfirmAppointment extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();

    //Get current user and username
    Member currentUser = (Member) session.getAttribute("currentUser");
    String currentUsername = currentUser.getUsername();

    //Retrieve lists for confirmed and pending appointments
    @SuppressWarnings("unchecked")
    ArrayList<AppointmentModel> confirmedAppointments = (ArrayList<AppointmentModel>) session.getAttribute("confirmedAppointments");
    @SuppressWarnings("unchecked")
    ArrayList<AppointmentModel> pendingAppointments = (ArrayList<AppointmentModel>) session.getAttribute("pendingAppointments");

    //Get appointment Id to change
    String appointmentId = request.getParameter("appSelect");
    int appointmentIdAsInt = Integer.parseInt(appointmentId);

    AppointmentModel currentAppointment = new AppointmentModel();

    //Update appointment info in database
    try
    {
      Connection conn = ConnectDB.getConnection();
      String sqlUpdate = "UPDATE tblAppointments SET isAccepted = 1 WHERE id = "+appointmentId;
      PreparedStatement ps = conn.prepareStatement(sqlUpdate);
      ps.executeUpdate();

      //Cleanup
      ps.close();
      conn.close();

      //Update session variables information
      for(int i = 0; i < pendingAppointments.size(); i++)
      {
        if (pendingAppointments.get(i).getAppId() == appointmentIdAsInt)
        {
          currentAppointment = pendingAppointments.get(i);
          currentAppointment.setAccepted(true);
          confirmedAppointments.add(currentAppointment);
          pendingAppointments.remove(i);
          break;
        }
      }

      //Add the updated lists to the session variables
      session.setAttribute("confirmedAppointments", confirmedAppointments);
      session.setAttribute("pendingAppointments", pendingAppointments);

      //Redirect user to LecturerAppointmentPage
      response.sendRedirect("appointmentPage.jsp");
    }
    catch(SQLException e)
    {
      response.sendRedirect("appointmentPage.jsp");
    }
	}
}
