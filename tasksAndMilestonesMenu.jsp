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

<html>
  <head>
    <meta charset="utf-8">
    <title>Tasks and Milestones</title>
    <script type="text/javascript">
      function validateMilestoneCreateForm()
      {
        if (document.milestoneCreateForm.milestone.value == "")
        {
          alert("Please enter a name for the milestone.");
          return false;
        }
        else
        {
            return true;
        }
      }
    </script>
  </head>

  <body>
    <div>
      <h1>Tasks and Milestones for <%= currentProject.getProjectName() %></h1>
      <a href="projectMenu.jsp">Return to Project Page</a>

      <!-- Display a list of tasks for the project -->
      <h2>Tasks</h2>
      <% if (tasks.size() > 0) { %>
      <form name="taskSelectForm" method="post" action="">
        <table border="solid 1"><tr><td>Task</td><td>Responsibility</td><td>Start Date</td><td>Due Date</td><td>Edit Task Info</td>
      <% for (int i=0; i < tasks.size(); i++) { %>
      <% TaskModel currentTask = tasks.get(i); %>
        <tr>
          <td><%= currentTask.getTaskname() %></td>
          <td><%= currentTask.getAssigneduser() %></td>
          <td><%= currentTask.getStartDate() %></td>
          <td><%= currentTask.getDueDate() %></td>
          <% if (i==0) { %>
          <td><input type="radio" name="selectedTask" value="<%= currentTask.getTaskname() %>" checked="true"></td>
          <% } else { %>
          <td><input type="radio" name="selectedTask" value="<%= currentTask.getTaskname() %>"></td>
          <% } %>
        </tr>
      <% } %>
        </table>
        <input type="submit" value="Edit Selected Task" />
      </form>
      <% } else { %>
      There are no tasks to display.<br />
      <% } %>
      <a href="">Create a new task</a>
      <!-- Display a list of milestones -->
      <h2>Milestones</h2>
      <% if (milestones.size() > 0) { %>
      <table border="solid 1"><tr><td>Milestone</td><td>Due Date</td><td>Completed</td><td></td><td></td></tr>
      <% for (int i=0; i < milestones.size(); i++) { %>
      <% MilestoneModel currentMilestone = milestones.get(i); %>
        <tr>
          <td><%= currentMilestone.getMilestone() %></td>
          <td><%= currentMilestone.getDueDate() %></td>
          <% if (currentMilestone.getIsComplete()) { %>
          <td>Yes</td>
          <td></td>
          <% } else { %>
          <td>No</td>
          <td><a href="?name=<%= currentMilestone.getMilestone() %>&function=complete">Mark Complete</a></td>
          <% } %>
          <td><a href="?name=<%= currentMilestone.getMilestone() %>&function=delete">Delete</a></td>
        </tr>
      <% } %>
      </table>
      <% } else { %>
      There are no milestones to display. <br />
      <% } %>
      <h3>Add a Milestone</h3>
      <form name=milestoneCreateForm method="post" action="createMilestone">
        Milestone: <input type="text" name="milestone"> <br />
        Due Date: Year: <select name="dateYear">
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
        <input type="submit" value="Add Milestone" onClick="return validateMilestoneCreateForm()">
      </form>

    </div>
  </body>
</html>
