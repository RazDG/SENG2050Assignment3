<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="mvc.TaskModel" %>
<jsp:useBean id="currentUser" scope="session" class="mvc.Member" />
<jsp:useBean id="currentProject" scope="session" class="mvc.ProjectModel" />
<jsp:useBean id="currentTask" scope="session" class="mvc.TaskModel" />

<%
  ArrayList<String> groupMembers = (ArrayList<String>) currentProject.getGroupMembers();
%>

<html>
  <head>
    <meta charset="utf-8">
    <title>Edit Task</title>
  </head>

  <body>
    <div>
      <h1>Edit Task: <%= currentTask.getTaskname() %></h1>
      <table border="solid 1">
        <tr><td>Attribute</td><td>Value</td></tr>
        <tr><td>Task</td><td><%= currentTask.getTaskname() %></td></tr>
        <tr><td>Responsibility</td><td><%= currentTask.getAssigneduser() %></td></tr>
        <tr><td>Start Date</td><td><%= currentTask.getStartDate() %></td></tr>
        <tr><td>Due Date</td><td><%= currentTask.getDueDate() %></td></tr>
      </table>
      <form name="changeAssignedUser" method="post" action="editTaskAssignedUser">
        <h3>Change Responsible User</h3>
        <select name="responsibleUser">
        <% for (int i = 0; i < groupMembers.size(); i++) { %>
          <option value="<%= groupMembers.get(i) %>"><%= groupMembers.get(i) %></option>
          <% } %>
        </select><br />
        <input type="submit" value="Change Responsible User" />
      </form>
      <form name="changeStartDate" method="post" action="editTaskStartDate">
        <h3>Change Start Date</h3>
        Year: <select name="startDateYear">
          <option value="2020">2020</option>
          <option value="2021">2021</option>
          <option value="2022">2022</option>
        </select>
        Month: <select name="startDateMonth">
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
        Day: <select name="startDateDay">
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
        <input type="submit" value="Change Start Date" />
      </form>
      <form name="changeDueDate" method="post" action="editTaskDueDate">
        <h3>Change Due Date</h3>
        Year: <select name="dueDateYear">
          <option value="2020">2020</option>
          <option value="2021">2021</option>
          <option value="2022">2022</option>
        </select>
        Month: <select name="dueDateMonth">
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
        Day: <select name="dueDateDay">
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
        <input type="submit" value="Change Due Date" />
      </form>
    </div>
  </body>

</html>
