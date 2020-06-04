<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<jsp:useBean id="currentUser" scope="session" class="mvc.Member" />
<jsp:useBean id="currentProject" scope="session" class="mvc.ProjectModel" />

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

      <a href="homePage.jsp">Return to Home Page</a>

      <!-- Group members for project listed-->
      <h2>Group Members</h2>
        <ul>
          <% ArrayList<String> groupMembers = currentProject.getGroupMembers();
          for (int i = 0; i < groupMembers.size(); i++) { %>
          <li><%= groupMembers.get(i) %></li>
          <% } %>
        </ul>
      <a href="addUserToProject">Add a new Group Member</a>

      <h2>Tasks and Milestones</h2>
      <a href="tasksAndMilestonesMenu.jsp">View Tasks and Milestones</a>

      <h2>Project Files</h2>
      <a href="">View Project Files</a>

      <h2>Peer Evaluation</h2>
      <a href="peerEvaluationPage.jsp">Go to Peer Evaluation</a>

      <h2>Overall Progress</h2>
      <a href="">View Overall Project Progress</a>

    </div>
  </body>
</html>
