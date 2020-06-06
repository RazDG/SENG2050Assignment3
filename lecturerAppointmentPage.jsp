<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="currentUser" scope="session" class="mvc.Member" />


<html>
<head>
  <meta charset="utf-8">
  <title>Appointments</title>
</head>
<body>
  <div>
    <h1>Appointments for <%= currentUser.getUsername() %></h1>
    <!-- Appointments that have been confirmed -->
    <div>
      <h2>Confirmed Appointments</h2>
    </div>

    <!-- Appointments pending confirmation -->
    <div>
      <h2>Pending Confirmation</h2>
    </div>

    <div>
      <h2>Send Appointment Request</h2>
    </div>
  </div>
</body>
</html>
