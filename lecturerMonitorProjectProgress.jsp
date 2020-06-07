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
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
    <div>
      <!-- Project title displayed -->
      <h1>Monitor Project Progress for <%= currentProject.getProjectName() %></h1>

      <% int Num=0; %>
      <table border="solid 1"><tr><td>Milestone</td><td>Due Date</td><td>Completed</td>
      <% for (int i=0; i < milestones.size(); i++) { %>
      <% MilestoneModel currentMilestone = milestones.get(i); %>
        <tr>
          <td><%= currentMilestone.getMilestone() %></td>
          <td><%= currentMilestone.getDueDate() %></td>
          <% if (currentMilestone.getIsComplete()) { %>
          <td>Yes</td>
          <% Num++; %>
          <% } else { %>
          <td>No</td>
          <% } %>
        </tr>
      <% } %>
      <a href="lecturerHomePage.jsp">Return to Home page</a><br><br>
      <!--Calculate persentage of completion (z) then display in the progress bar-->
      <% double z = (Double.valueOf(Num)/Double.valueOf(milestones.size()))*100; %>
      <label for="progressBar">Project Progress: </label>
      <progress value="<%= (int)z %>" max="100" id="progressBar">0%</progress>
    </div>
  </body>
</html>
