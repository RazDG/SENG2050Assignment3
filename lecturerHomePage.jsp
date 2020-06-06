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
  </head>

  <body>
    <div>
      <h1>University Group Project Management</h1>
      <p>Logged in as: <%= currentUser.getUsername() %></p>
      <h2>View Student Projects</h2>
      <% if (projects.size() > 0) { %>
      <form name="projectSelectForm" method="post" action="">
      <% for (int i = 0; i < projects.size(); i++) { %>
      <% if (i == 0) { %>
        <input type="radio" name="project" value="<%= projects.get(i) %>" checked="true"><%= projects.get(i) %>
      <% } else { %>
        <input type="radio" name="project" value="<%= projects.get(i) %>"><%= projects.get(i) %>
      <% } %>
      <% } %>
        <input type="submit" value="View Student Project" />
      </form>
      <% } %>
    </div>
  </body>
</html>
