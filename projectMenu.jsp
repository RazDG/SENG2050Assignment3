<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<jsp:useBean id="currentUser" scope="session" class="mvc.Member">
<jsp:useBean id="currentProject" scope="session" class="mvc.ProjectModel">

<!DOCTYPE html>
<html lang="eng">
  <head>
    <meta charset="utf-8">
    <title>Project Menu</title>
  </head>

  <body>
    <div>
      <!-- Project title displayed -->
      <h1>Project: <%= currentProject.getProjectName() %></h1>

      <!-- Group members for project listed-->
      <h2>Group Members</h2>
        <ul>
          <% ArrayList<String> groupMembers = (ArrayList<String>) session.getAttribute("projectGroupMembers");
          for (int i = 0; i < groupMembers.size(); i++) { %>
          <li><%= groupMembers.get(i) %></li>
          <% } %>
        </ul>
      <a href="">Add a new Group Member</a>

      <a href=""><h2>Tasks</h2></a>

      <a href=""><h2>Project Files</h2></a>

      <a href=""><h2>Peer Evaluation</h2></a>

    </div>
  </body>
</html>
