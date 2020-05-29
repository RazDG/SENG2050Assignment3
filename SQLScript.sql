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
INSERT INTO tblUser
VALUES ('test1', 'abc', 'Student');

--Table for Group Projects
CREATE TABLE tblGroupProject(
  id INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  projectname VARCHAR(80),
);

--Table for mapping Users to Projects
CREATE TABLE tblGroupProjectUsers(
  projectname VARCHAR(80),
  username VARCHAR(80),
  PRIMARY KEY (projectname, username),
  FOREIGN KEY (projectname) REFERENCES tblGroupProject(projectname),
  FOREIGN KEY (username) REFERENCES tblUser(username)
);

--Table for Group Project Tasks
CREATE TABLE tblGroupProjectTasks(
  projectname VARCHAR(80),
  taskname VARCHAR(80),
  assigneduser VARCHAR(80),
  PRIMARY KEY (projectname, taskname),
  FOREIGN KEY (projectname) REFERENCES tblGroupProject(projectname),
  FOREIGN KEY (assigneduser) REFERENCES tblUser(username)
);
