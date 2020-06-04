<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<jsp:useBean id="currentUser" scope="session" class="mvc.Member" />
<jsp:useBean id="currentProject" scope="session" class="mvc.ProjectModel" />

<% ArrayList<String> groupMembers = currentProject.getGroupMembers(); %>

<html>
  <head>
    <meta charset="utf-8">
    <title>Peer Evaluation Menu</title>
    <script type="text/javascript">
      function validateEvaluationForm(){
        if (document.EvaluationForm.selectedStudent.value == "")
        {
          alert("Please select a student");
          return false;
        }
        else {
          alert(""+document.EvaluationForm.selectedStudent.value+" is evaluated");
          return true;
        }
      }
    </script>
  </head>

  <body>
    <div>
      <h1>Peer Evaluation for <%= currentProject.getProjectName() %></h1>

      <!-- Display group members of the project-->
      <h3>Group Members of the Project: </h3>
        <!-- Form is created with all available students list of project -->
        <% if (groupMembers.size() > 0) { %>
        <form name="EvaluationForm" method="post" action="">
          <% for (int i = 0; i < groupMembers.size(); i++) { %>
          <input type="radio" name="selectedStudent" value="<%= groupMembers.get(i)%>"><%= groupMembers.get(i)%> <br />
          <textarea name="comment" rows="4" cols="40" placeholder="Please enter the comments here!!!"></textarea><br /><br />
          <% } %>
          <input type="submit" value="Submit" onClick="return validateEvaluationForm()" />
        </form>
        <% } else { %>
        There are no available students.<br />
        <% } %>

        <a href="projectMenu.jsp">Return to Project Menu</a>
    </div>
  </body>
</html>
