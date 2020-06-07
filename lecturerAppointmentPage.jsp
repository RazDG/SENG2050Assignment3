<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="mvc.AppointmentModel" %>
<jsp:useBean id="currentUser" scope="session" class="mvc.Member" />

<%
  ArrayList<AppointmentModel> confirmedAppointments = (ArrayList<AppointmentModel>) session.getAttribute("confirmedAppointments");
  ArrayList<AppointmentModel> pendingAppointments = (ArrayList<AppointmentModel>) session.getAttribute("pendingAppointments");
%>

<html>
<head>
  <meta charset="utf-8">
  <title>Appointments</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <div>
    <h1>Appointments for <%= currentUser.getUsername() %></h1>
    <!-- Appointments that have been confirmed -->
    <div>
      <h2>Confirmed Appointments</h2>
      <%if (confirmedAppointments.size() > 0) { %>
      <table><tr><td>Meeting Attendees:</td><td>Date:</td><td>Time:</td></tr>
      <% for (int i = 0; i < confirmedAppointments.size(); i++) { %>
        <tr>
          <td><%= confirmedAppointments.get(i).getUserSender() %>, <% confirmedAppointments.get(i).getUserReceiver() %></td>
          <td><%= confirmedAppointments.get(i).getAppdate() %></td>
          <td><%= confirmedAppointments.get(i).getApptime() %></td>
        </tr>
      <% } %>
      </table>
      <% } else { %>
      There are no confirmed appointments.
      <% } %>
    </div>

    <!-- Appointments pending confirmation -->
    <div>
      <h2>Pending Confirmation</h2>
    </div>
    <%if (pendingAppointments.size() > 0) { %>
    <form name="confirmAppointmentForm" method="post" action="confirmLecturerAppointment">
      <table><tr><td>Meeting Requested By:</td><td>Date:</td><td>Time:</td><td>Confirm Appointment</td></tr>
    <% for (int i = 0; i < pendingAppointments.size(); i++) { %>
        <tr>
          <td><%= confirmedAppointments.get(i).getUserSender() %></td>
          <td><%= confirmedAppointments.get(i).getAppdate() %></td>
          <td><%= confirmedAppointments.get(i).getApptime() %></td>
          <% if (i == 0) { %>
          <td><input type="radio" name="appSelect" value="<%= pendingAppointments.get(i).getAppId() %>" checked="true"></td>
          <% } else { %>
          <td><input type="radio" name="appSelect" value="<%= pendingAppointments.get(i).getAppId() %>"></td>
          <% } %>
        </tr>
    <% } %>
      </table>
      <input type="submit" value="Confirm Selected Appointment" />
    </form>
    <% } else { %>
    There are no pending appointments.
    <% } %>
    <div>
      <h2>Send Appointment Request</h2>
    </div>
  </div>
</body>
</html>
