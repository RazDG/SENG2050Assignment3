# SENG2050Assignment3

This is the Group Work Management System Project for SENG2050 Assignment 3.

System design:
  - Log In Page:
    1. User types in a Username and Password, then clicks submit button
    2. Directs to a Java Servlet that handles the data called "LoadUser.java"
    3. LoadUser.java checks the database to see if the user exists, and if they do, their information is collected including Username, Password, Usertype (Student or Lecturer). This information is stored in an instance of the "Member" class.
    4. The Member class object containing all user information is assigned to a session variable called "currentUser"
    5. When all this is done, the user is then redirected to a Java Servlet (that will process some information, find which projects they are in) and then to their home page (I'm thinking of doing 2 separate home pages for students and lecturers)

  - Student Home Page (homePage.jsp):
    1. The main page of the app. Will display the name of the logged in User, a list of the projects that they are apart of as hyperlinks (Each project has its own homepage), a link to create a new project, a link to the "Appointments" page.
    2. If the user clicks on a Project link, they are redirected to a Java Servlet that will collect all the necessary information on that project to build the page, then redirect the user to the Page for that project.
    3. 

  - Project Page
    1. Each project has a project page, upon loading
