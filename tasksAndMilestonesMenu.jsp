<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<jsp:useBean id="currentUser" scope="session" class="mvc.Member" />
<jsp:useBean id="currentProject" scope="session" class="mvc.ProjectModel" />

<html>
  <head>
    <meta charset="utf-8">
    <title>Tasks and Milestones</title>
  </head>

  <body>
    <div>
      <h1>Tasks and Milestones for <%= currentProject.getProjectName() %></h1>
      <!-- Display a list of tasks for the project -->
      <h2>Tasks</h2>

      <!-- Display a list of milestones -->
      <h2>Milestones</h2>
      
    </div>
  </body>
</html>
