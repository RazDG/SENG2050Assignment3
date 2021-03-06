<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<jsp:useBean id="currentUser" scope="session" class="mvc.Member" />
<jsp:useBean id="currentProject" scope="session" class="mvc.ProjectModel" />

<% ArrayList<String> studentList = (ArrayList<String>) session.getAttribute("availableUsers"); %>

<html>
  <head>
    <meta charset="utf-8">
    <title>Add a User to <%= currentProject.getProjectName()%></title>
    <link rel="stylesheet" href="css/style.css">

    <script type="text/javascript">
      function validateAddUserForm()
      {
        if (document.addUserForm.selectedStudent.value == "")
        {
          alert("Please select a student");
          return false;
        }
        else {
          alert("Adding student "+document.addUserForm.selectedStudent.value+" to the project");
          return true;
        }
      }
    </script>
  </head>

  <body>
    <div>
      <h1>Add a User to <%= currentProject.getProjectName()%></h1>
      <!-- Form is created with all available students listed -->
      <% if (studentList.size() > 0) { %>
      <form name="addUserForm" method="post" action="finaliseAddUserToProject">
        <% for (int i = 0; i < studentList.size(); i++) { %>
        <input type="radio" name="selectedStudent" value="<%= studentList.get(i)%>"><%= studentList.get(i)%> <br />
        <% } %>
        <input type="submit" value="Add User" onClick="return validateAddUserForm()" />
      </form>
      <% } else { %>
      There are no available students.<br />
      <% } %>
      <a href="projectMenu.jsp">Return to Project Menu</a>
    </div>
  </body>
</html>
