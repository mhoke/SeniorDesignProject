/*TODO Add the drop table statements here*/

CREATE CACHED TABLE Assignments(
  id INTEGER IDENTITY, 
  course_id INTEGER NOT NULL,
  student_id INTEGER NOT NULL,
  name VARCHAR(80) NOT NULL,
  due_date DATE NOT NULL,
  grade_weight_type INTEGER NOT NULL,
  earned_points INTEGER NOT NULL,
  possible_points INTEGER NOT NULL		
 );

CREATE CACHED TABLE Grade_Weights (
	id INTEGER IDENTITY,
  	name VARCHAR(80) NOT NULL,
  	weight INTEGER NOT NULL,
	course_id INTEGER NOT NULL
);

CREATE CACHED TABLE Courses (
  	id INTEGER IDENTITY, 
  	name VARCHAR(80) NOT NULL, 
  	professor_id INTEGER NOT NULL,
  	time VARCHAR(80) NOT NULL, 
  	course_num INTEGER NOT NULL,
	sec_num INTEGER NOT NULL,
	credits INTEGER NOT NULL,
	weekly_days VARCHAR(80) NOT NULL,
	location VARCHAR(80) NOT NULL,
	crn INTEGER NOT NULL,
	description VARCHAR(80) NOT NULL
);

CREATE CACHED TABLE Enrolled_Courses (
 	id INTEGER IDENTITY, 
 	student_id INTEGER NOT NULL, 
	professor_id INTEGER NOT NULL, 
	course_id INTEGER NOT NULL,
	grade INTEGER NOT NULL
);

CREATE CACHED TABLE Users (
	id INTEGER IDENTITY, 
	username VARCHAR(80) NOT NULL,
	name VARCHAR(80) NOT NULL,
	password VARCHAR(32) NOT NULL,
	salt VARCHAR(16) NOT NULL,
	emailAddress VARCHAR(80) NOT NULL,
	userType INTEGER NOT NULL,
	major VARCHAR(80) NOT NULL,
	commuter BOOLEAN NOT NULL
);

CREATE CACHED TABLE Admins (
	id INTEGER IDENTITY, 
	username VARCHAR(80) NOT NULL,
	password VARCHAR(32) NOT NULL,
	salt VARCHAR(16) NOT NULL,
);

CREATE CACHED TABLE Buildings (
	id INTEGER IDENTITY,
	building_name VARCHAR(80) NOT NULL
);

/* Run this after creating all the tables*/
INSERT INTO Buildings values(NULL, 'KEC');
INSERT INTO Buildings values(NULL, 'HUM');
INSERT INTO Buildings values(NULL, 'CH');
INSERT INTO Buildings values(NULL, 'LS');
INSERT INTO Buildings values(NULL, 'GH');
INSERT INTO Buildings values(NULL, 'WOLF');
INSERT INTO Buildings values(NULL, 'GC');
INSERT INTO Buildings values(NULL, 'MKH');
INSERT INTO Buildings values(NULL, 'LIBRY');
INSERT INTO Buildings values(NULL, 'NESC');

/* Test data*/
/* The password for all the test users is "password"*/
INSERT INTO Users values(NULL,'TestStudent', 'John Smith', '075293660cfd83e0644b52d5703243cc ', '7c99cda63beb37f8', 'teststudent@whiteboard.org', 1, 'CS', true);
INSERT INTO Users values(NULL,'TestProfessor', 'Bill Smith', '075293660cfd83e0644b52d5703243cc ', '7c99cda63beb37f8', 'testprofessor@whiteboard.org', 2, 'NONE', false);
INSERT INTO Users values(NULL,'TestBoth', 'Ron John', '075293660cfd83e0644b52d5703243cc ', '7c99cda63beb37f8', 'testboth@whiteboard.org', 3, 'CS', true);
INSERT INTO Users values(NULL,'msteppe', 'Mike Steppe', '075293660cfd83e0644b52d5703243cc ', '7c99cda63beb37f8', 'msteppe@whiteboard.org', 1, 'CS', false);
INSERT INTO Users values(NULL,'mhoke', 'Mike Hoke', '075293660cfd83e0644b52d5703243cc ', '7c99cda63beb37f8', 'mhoke@whiteboard.org', 1, 'CS', true);
INSERT INTO Users values(NULL,'pvo', 'Paul Vo', '075293660cfd83e0644b52d5703243cc ', '7c99cda63beb37f8', 'pvo@whiteboard.org', 1, 'CS', false);
INSERT INTO Users values(NULL,'nbrady', 'Nick Brady', '075293660cfd83e0644b52d5703243cc ', '7c99cda63beb37f8', 'nbrady@whiteboard.org', 1, 'CS', true);

INSERT INTO Courses values(NULL, 'Calculus I', 1, '12:00-12:50', 101, 101, 4, 'MWF', 'CHM 223', 99999, 'This is a calculus I class.');
INSERT INTO Courses values(NULL, 'CPADS', 1, '1:00-2:40', 101, 101, 4, 'WF', 'CS 119', 88888, 'This is a CPADS class.');
INSERT INTO Courses values(NULL, 'Spanish I', 2, '1:00-1:50', 101, 101, 4, 'MWF', 'CHM 301', 77777, 'This is a spanish I class.');
INSERT INTO Courses values(NULL, 'Spanish II', 2, '2:00-2:50', 201, 104, 4, 'TR', 'CHM 201', 66666, 'This is a spanish II class.');

INSERT INTO Enrolled_Courses values(NULL, 0, 1, 0, 85);
INSERT INTO Enrolled_Courses values(NULL, 0, 2, 2, 100);
INSERT INTO Enrolled_Courses values(NULL, 2, 1, 1, 65);
INSERT INTO Enrolled_Courses values(NULL, 1, 1, 1, 75);

INSERT INTO Assignments values(NULL, 0, 1, 'Homework 1', '2012-09-08', 1, 100, 100);
INSERT INTO Assignments values(NULL, 0, 2, 'Homework 1', '2012-09-08', 1, 90, 100);
INSERT INTO Assignments values(NULL, 1, 12, 'Quiz 3', '2012-12-08', 0, 45, 50);
INSERT INTO Assignments values(NULL, 2, 20, 'Homework 6', '2015-01-06', 1, 81, 130);
INSERT INTO Assignments values(NULL, 3, 1, 'Homework 2', '2012-01-19', 1, 7, 10);
INSERT INTO Assignments values(NULL, 3, 1, 'Test 1', '2012-05-05', 2, 112, 120);
INSERT INTO Assignments values(NULL, 3, 1, 'Extra Credit 4', '2012-10-10', 3, 89, 100);
INSERT INTO Assignments values(NULL, 4, 20, 'Homework 1', '2012-11-11', 1, 83, 90);
INSERT INTO Assignments values(NULL, 5, 12, 'Homework 1', '2012-12-12', 1, 15, 17);


INSERT INTO Admins values(NULL, 'TestAdmin', '075293660cfd83e0644b52d5703243cc ', '7c99cda63beb37f8');
