<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="mvc.AppointmentModel" %>
<jsp:useBean id="currentUser" scope="session" class="mvc.Member" />

<%
  ArrayList<AppointmentModel> confirmedAppointments = (ArrayList<AppointmentModel>) session.getAttribute("confirmedAppointments");
  ArrayList<AppointmentModel> pendingAppointments = (ArrayList<AppointmentModel>) session.getAttribute("pendingAppointments");
  ArrayList<String> usersList = (ArrayList<String>) session.getAttribute("usersList");
%>

<html>
<head>
  <meta charset="utf-8">
  <title>Appointments</title>
  <link rel="stylesheet" href="css/style.css">
  <script type="text/javascript">
    function validateNewAppointmentForm()
    {
      alert("Appointment Request has been sent to the recipient");
      return true;
    }
  </script>
</head>
<body>
  <div>
    <h1>Appointments for <%= currentUser.getUsername() %></h1>
    <!-- Appointments that have been confirmed -->
    <div>
      <h2>Confirmed Appointments</h2>
      <%if (confirmedAppointments.size() > 0) { %>
      <table border="solid 1"><tr><td>Meeting Attendees:</td><td>Date:</td><td>Time:</td></tr>
      <% for (int i = 0; i < confirmedAppointments.size(); i++) { %>
        <tr>
          <td><%= confirmedAppointments.get(i).getUserSender() %>,
          <%= confirmedAppointments.get(i).getUserReceiver() %></td>
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
    <form name="confirmAppointmentForm" method="post" action="confirmAppointment">
      <table border="solid 1"><tr><td>Meeting Requested By:</td><td>Date:</td><td>Time:</td><td>Confirm Appointment</td></tr>
    <% for (int i = 0; i < pendingAppointments.size(); i++) { %>
        <tr>
          <td><%= pendingAppointments.get(i).getUserSender() %></td>
          <td><%= pendingAppointments.get(i).getAppdate() %></td>
          <td><%= pendingAppointments.get(i).getApptime() %></td>
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
      <form name="newAppointmentForm" method="post" action="createAppointment">
        User: <select name="userSelect">
        <% for (int i = 0; i < usersList.size(); i++) { %>
          <option value="<%= usersList.get(i) %>"><%= usersList.get(i) %></option>
        <% } %>
        </select> <br />
        Date: Year: <select name="dateYear">
          <option value="2020">2020</option>
          <option value="2021">2021</option>
          <option value="2022">2022</option>
        </select>
        Month: <select name="dateMonth">
          <option value="01">January</option>
          <option value="02">February</option>
          <option value="03">March</option>
          <option value="04">April</option>
          <option value="05">May</option>
          <option value="06">June</option>
          <option value="07">July</option>
          <option value="08">August</option>
          <option value="09">September</option>
          <option value="10">October</option>
          <option value="11">November</option>
          <option value="12">December</option>
        </select>
        Day: <select name="dateDay">
          <option value="01">01</option>
          <option value="02">02</option>
          <option value="03">03</option>
          <option value="04">04</option>
          <option value="05">05</option>
          <option value="06">06</option>
          <option value="07">07</option>
          <option value="08">08</option>
          <option value="09">09</option>
          <option value="10">10</option>
          <option value="11">11</option>
          <option value="12">12</option>
          <option value="13">13</option>
          <option value="14">14</option>
          <option value="15">15</option>
          <option value="16">16</option>
          <option value="17">17</option>
          <option value="18">18</option>
          <option value="19">19</option>
          <option value="20">20</option>
          <option value="21">21</option>
          <option value="22">22</option>
          <option value="23">23</option>
          <option value="24">24</option>
          <option value="25">25</option>
          <option value="26">26</option>
          <option value="27">27</option>
          <option value="28">28</option>
          <option value="29">29</option>
          <option value="30">30</option>
          <option value="31">31</option>
        </select> <br />
        <select name="time">
          <option value="1">1</option>
          <option value="1:30">1:30</option>
          <option value="2">2</option>
          <option value="2:30">2:30</option>
          <option value="3">3</option>
          <option value="3:30">3:30</option>
          <option value="4">4</option>
          <option value="4:30">4:30</option>
          <option value="5">5</option>
          <option value="5:30">5:30</option>
          <option value="6">6</option>
          <option value="6:30">6:30</option>
          <option value="7">7</option>
          <option value="7:30">7:30</option>
          <option value="8">8</option>
          <option value="8:30">8:30</option>
          <option value="9">9</option>
          <option value="9:30">9:30</option>
          <option value="10">10</option>
          <option value="10:30">10:30</option>
          <option value="11">11</option>
          <option value="11:30">11:30</option>
          <option value="12">12</option>
          <option value="12:30">12:30</option>
        </select>
        AM/PM <select name="amPm">
          <option value="am">am</option>
          <option value="pm">pm</option>
        </select> <br />
        <input type="submit" value="Send Meeting Request" onClick="return validateNewAppointmentForm()" />
      </form>
    </div>
  </div>
</body>
</html>
