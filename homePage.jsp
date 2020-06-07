<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<jsp:useBean id="currentUser" scope="session" class="mvc.Member" />


<!DOCTYPE html>
<html lang="eng">
  <head>
    <meta charset="utf-8">
    <title>Home</title>
    <script type="text/javascript">
      function validateProjectSelectForm()
      {
        if (document.projectSelectForm.projectSelect.value == "")
        {
          alert("Please select a project");
          return false;
        }
        else {
          alert("Opening project: "+document.projectSelectForm.projectSelect.value);
          return true;
        }
      }
      function validateCreateProjectForm()
      {
        if (document.createProjectForm.newProjectName.value == "")
        {
          alert("Please enter a project name");
          return false;
        }
        else {
          alert("Creating project for "+document.createProjectForm.newProjectName.value);
          return true;
        }
      }
    </script>
  </head>

  <body>
    <div>
      <h1>University Group Project Management</h1>
      <p>
        Logged in as: <strong><%= currentUser.getUsername() %></strong> <br />
        <a href="index.jsp">Log Out</a>
      </p>
      <!-- Displays a list of this user's projects -->
      <h2>Your Projects:</h2>
      <% ArrayList<String> currentUserProjects = currentUser.getProjects();
      if (currentUserProjects.size() > 0) { %>
      <form name="projectSelectForm" method="post" action="loadProjectMenu">
        <% for (int i = 0; i < currentUserProjects.size(); i++) { %>
        <% if (i == 0) { %>
        <input type="radio" name="projectSelect" value="<%= currentUserProjects.get(i) %>" checked="true"> <%= currentUserProjects.get(i) %> <br />
        <% } else { %>
        <input type="radio" name="projectSelect" value="<%= currentUserProjects.get(i) %>"> <%= currentUserProjects.get(i) %> <br />
        <% } } %>
        <input type="submit" value="View Project" onClick="return validateProjectSelectForm()" />
      </form>
      <% } else { %>
      No projects to display.
      <% } %>
      <h2>Create a new Project</h2>
      <form name="createProjectForm" method="post" action="createNewProject">
        <input type="text" name="newProjectName">
        <input type="submit" value="Create Project" onClick="return validateCreateProjectForm()" />
      </form>
      <h2>Appointments</h2>
      <a href="arrangeAppointment.jsp">View Appointments</a>

    </div>
  </body>
</html>
