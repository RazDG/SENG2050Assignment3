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

  - Project Page (The main hub for each project)
    1. Each project has a project page, data on the specific project is collected prior to loading the page (such as Group Members, Tasks, Project Files) and stored in a Java Bean.
    2. A list of the project's group members is displayed, a link to add a new group member, a list of tasks (as hyperlinks) with maybe some task info such as Milestones displayed, a link to create a new task, a link to the project files page, a link to the Peer Evaluation submission page

  - Project Files Page

  - Task Page
    1. Data is collected on the specific task prior to loading page and stored in Java Bean.
    2. Data displayed will include the task name and Milestones such as start date, due date, submission date.

  - Appointments Page

  - Lecturer Home Page
