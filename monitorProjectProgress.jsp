<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="mvc.TaskModel" %>
<%@page import="mvc.MilestoneModel" %>
<jsp:useBean id="currentUser" scope="session" class="mvc.Member" />
<jsp:useBean id="currentProject" scope="session" class="mvc.ProjectModel" />

<%
  ArrayList<TaskModel> tasks = (ArrayList<TaskModel>) session.getAttribute("currentTasks");
  ArrayList<MilestoneModel> milestones = (ArrayList<MilestoneModel>) session.getAttribute("currentMilestones");
%>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Overall Progress</title>
  </head>
  <body>
    <div class="">
      <!-- Project title displayed -->
      <h1>Monitor Project Progress for <%= currentProject.getProjectName() %></h1>

      <table border="solid 1"><tr><td>Task</td><td>Responsibility</td><td>Start Date</td><td>Due Date</td><td>Completed</td>
      <% for (int i=0; i < tasks.size(); i++) { %>
      <% TaskModel currentTask = tasks.get(i); %>
        <tr>
          <td><%= currentTask.getTaskname() %></td>
          <td><%= currentTask.getAssigneduser() %></td>
          <td><%= currentTask.getStartDate() %></td>
          <td><%= currentTask.getDueDate() %></td>
          <% for (int j=0; j < milestones.size(); j++) { %>
          <% MilestoneModel currentMilestone = milestones.get(j); %>
            <% if (currentMilestone.getIsComplete()) { %>
            <td>Yes</td>
            <td></td>
            <% } else { %>
            <td>No</td>
            <% } %>
          <% } %>
        </tr>
      <% } %>

      <a href="projectMenu.jsp">Return to Project Menu</a><br><br>
      <label for="progressBar">Project Progress: </label>
      <progress value="0" max="100" id="progressBar">0%</progress>
    </div>
  </body>
</html>