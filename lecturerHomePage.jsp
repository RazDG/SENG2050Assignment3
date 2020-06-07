<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<jsp:useBean id="currentUser" scope="session" class="mvc.Member" />

<%
  ArrayList<String> projects = (ArrayList<String>) session.getAttribute("currentStudentProjects");
%>

<html>
  <head>
    <meta charset="utf-8">
    <title>Lecturer Home Page</title>
    <link rel="stylesheet" href="css/style.css">
  </head>

  <body>
    <div>
      <h1>University Group Project Management</h1>
      <p>
        Logged in as: <strong><%= currentUser.getUsername() %></strong><br />
        <a href="index.jsp">Log Out</a>
      </p>

      <h2>View Student Projects</h2>
      <% if (projects.size() > 0) { %>
      <form name="projectSelectForm" method="post" action="">
      <% for (int i = 0; i < projects.size(); i++) { %>
      <% if (i == 0) { %>
        <input type="radio" name="project" value="<%= projects.get(i) %>" checked="true"><%= projects.get(i) %> <br />
      <% } else { %>
        <input type="radio" name="project" value="<%= projects.get(i) %>"><%= projects.get(i) %> <br />
      <% } %>
      <% } %>
        <input type="submit" value="View Student Project" />
      </form>
      <% } %>
      <h2>View Appointments</h2>
      <a href="loadLecturerAppointmentPage">View Appointments</a>
    </div>
  </body>
</html>
