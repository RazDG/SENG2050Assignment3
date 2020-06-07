<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<jsp:useBean id="currentProject" scope="session" class="mvc.ProjectModel" />

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Project Files</title>
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
    <div>
      <!-- Project title displayed -->
      <h1>Project Files for <%= currentProject.getProjectName() %></h1>

      Upload or download files please click <a href="https://1drv.ms/u/s!Ap3-Z-0CKmafkH-ZPjQopo4M_F5o?e=0HEETF">here</a>.<br><br>
      <a href="projectMenu.jsp">Return to Project Menu</a>
    </div>

  </body>
</html>
