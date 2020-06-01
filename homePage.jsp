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
<jsp:useBean id="currentUser" scope="session" class="mvc.Member" />

<!DOCTYPE html>
<html lang="eng">
  <head>
    <meta charset="utf-8">
    <title>Home</title>
  </head>

  <body>
    <div>
      <h1>University Group Project Management</h1>
      <p>Logged in as: <%= currentUser.getUsername() %></p>
      <!-- Displays a list of this users projects -->
      <h2>Your Projects:</h2>
      ----LIST OF PROJECTS HERE----
      <h2>Create a new Project</h2>
      <form name="createProjectForm" method="post">
        <input type="text" name="newProjectName">
        <input type="submit" value="Create Project" />
      </form>
      <h2><a href="">Appointments</a></h2>

    </div>
  </body>
</html>
