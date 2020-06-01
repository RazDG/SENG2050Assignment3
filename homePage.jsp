<h1>Projects</h1>
<div class="">member: </div>
<a href="addUser.jsp">Add Member</a><br>
<a href="setMilestones.jsp">Set Milestones</a><br>
<a href="tasks.jsp">Create Tasks</a><br>
<a href="accessProjectFiles.jsp">Access Project Files</a><br>
<a href="monitorProjectProgress.jsp">Monitor Project Progress</a><br>
<a href="submitPeerEvaluation.jsp">Submit Peer Evaluation</a><br>
<a href="arrangeAppointment.jsp">Arrange Appointment</a><br>

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
          return true;
        }
      }
    </script>
  </head>

  <body>
    <div>
      <h1>University Group Project Management</h1>
      <p>Logged in as: <%= currentUser.getUsername() %></p>
      <!-- Displays a list of this user's projects -->
      <h2>Your Projects:</h2>
      <% ArrayList<String> currentUserProjects = currentUser.getProjects();
      if (currentUserProjects.size() > 0) { %>
      <form name="projectSelectForm" method="post" action="loadProjectMenu">
        <% for (int i = 0; i < currentUserProjects.size(); i++) { %>
        <input type="radio" name="projectSelect" value="<%= currentUserProjects.get(i) %>"> <%= currentUserProjects.get(i) %> <br />
        <% } %>
        <input type="submit" value="View Project" onClick="return validateProjectSelectForm()" />
      </form>
      <% } else { %>
      No projects to display.
      <% } %>
      <h2>Create a new Project</h2>
      <form name="createProjectForm" method="post">
        <input type="text" name="newProjectName">
        <input type="submit" value="Create Project" />
      </form>
      <h2><a href="">Appointments</a></h2>

    </div>
  </body>
</html>
