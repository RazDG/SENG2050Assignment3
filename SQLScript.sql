CREATE DATABASE GroupProjectDB;
USE GroupProjectDB;

CREATE LOGIN GroupProjectDBUser
WITH PASSWORD = 'password';

CREATE USER GroupProjectDBUser
FOR LOGIN GroupProjectDBUser;

GRANT SELECT, INSERT, UPDATE, DELETE
TO GroupProjectDBUser;

--Table for Users
--Usertype determines if user is 'Student' or 'Lecturer'
CREATE TABLE tblUser(
  username VARCHAR(80) PRIMARY KEY,
  password VARCHAR(80),
  usertype VARCHAR(8) CHECK (usertype='Student' OR usertype='Lecturer')
);
--for testing
INSERT INTO tblUser VALUES ('Student1', 'abc', 'Student');
INSERT INTO tblUser VALUES ('Student2', 'abc', 'Student');
INSERT INTO tblUser VALUES ('Lecturer', 'abc', 'Lecturer');

--Table for Group Projects
CREATE TABLE tblGroupProject(
  projectname VARCHAR(80) PRIMARY KEY,
);
--for testing
INSERT INTO tblGroupProject VALUES ('TestProject');

--Table for mapping Users to Projects
CREATE TABLE tblGroupProjectUsers(
  projectname VARCHAR(80),
  username VARCHAR(80),
  PRIMARY KEY (projectname, username),
  FOREIGN KEY (projectname) REFERENCES tblGroupProject(projectname),
  FOREIGN KEY (username) REFERENCES tblUser(username)
);
--for testing
INSERT INTO tblGroupProjectUsers VALUES ('TestProject', 'Student1');

--Table for Group Project Tasks
CREATE TABLE tblGroupProjectTasks(
  projectname VARCHAR(80),
  taskname VARCHAR(80),
  assigneduser VARCHAR(80),
  startDate DATE,
  dueDate DATE,
  submissionDate DATE,
  PRIMARY KEY (projectname, taskname),
  FOREIGN KEY (projectname) REFERENCES tblGroupProject(projectname),
  FOREIGN KEY (assigneduser) REFERENCES tblUser(username)
);
