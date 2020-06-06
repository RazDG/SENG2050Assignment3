--Drop Tables
DROP TABLE IF EXISTS tblGroupProjectMilestones;
DROP TABLE IF EXISTS tblGroupProjectTasks;
DROP TABLE IF EXISTS tblGroupProjectUsers;
DROP TABLE IF EXISTS tblGroupProject;
DROP TABLE IF EXISTS tblAppointments;
DROP TABLE IF EXISTS tblUser;

--Drop and Recreate Database
DROP DATABASE IF EXISTS GroupProjectDB;
CREATE DATABASE GroupProjectDB;
USE GroupProjectDB;

CREATE LOGIN GroupProjectDBUser
WITH PASSWORD = 'password';

CREATE USER GroupProjectDBUser
FOR LOGIN GroupProjectDBUser;

GRANT SELECT, INSERT, UPDATE, DELETE
TO GroupProjectDBUser;

--Create Tables

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
  projectname VARCHAR(80) NOT NULL,
  taskname VARCHAR(100) NOT NULL,
  assigneduser VARCHAR(80),
  startDate VARCHAR(20),
  dueDate VARCHAR(20),
  PRIMARY KEY (projectname, taskname),
  FOREIGN KEY (projectname) REFERENCES tblGroupProject(projectname),
  FOREIGN KEY (assigneduser) REFERENCES tblUser(username)
);
--for testing
INSERT INTO tblGroupProjectTasks VALUES ('TestProject', 'Task1', 'Student1', '2020-06-03', '2020-06-06');
INSERT INTO tblGroupProjectTasks VALUES ('TestProject', 'Task2', null, null, null);

CREATE TABLE tblAppointments (
  id INT IDENTITY(1,1) PRIMARY KEY,
  userSender VARCHAR(80) NOT NULL,
  userReceiver VARCHAR(80) NOT NULL,
  appdate VARCHAR(20),
  apptime TIME,
  isAccepted BIT,
  FOREIGN KEY (userSender) REFERENCES tblUser(username),
  FOREIGN KEY (userReceiver) REFERENCES tblUser(username)
);

--Table for milestones
CREATE TABLE tblGroupProjectMilestones (
  projectname VARCHAR(80) NOT NULL,
  milestone VARCHAR(80) NOT NULL,
  duedate VARCHAR(20),
  isComplete BIT NOT NULL,
  FOREIGN KEY (projectname) REFERENCES tblGroupProject(projectname)
);
--for testing
INSERT INTO tblGroupProjectMilestones VALUES ('TestProject', 'Submit Work', '2020-06-06', false);

CREATE TABLE tbEvaluate (
  projectName VARCHAR(80),
  selectedUser VARCHAR(80),
  comments VARCHAR(200),
  PRIMARY KEY (projectName, selectedUser, authorUser),
  FOREIGN KEY (projectName) REFERENCES tblGroupProject(projectname),
  FOREIGN KEY (selectedUser) REFERENCES tblUser(username)
);
