CREATE CACHED TABLE Assignments(
  id INTEGER IDENTITY, 
  course_id INTEGER NOT NULL,
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
	password VARCHAR(32) NOT NULL,
	salt VARCHAR(16) NOT NULL,
	emailAddress VARCHAR(80) NOT NULL,
	userType INTEGER NOT NULL,
	major VARCHAR(80) NOT NULL,
	resident BOOLEAN NOT NULL
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
INSERT INTO Users values(NULL,'TestStudent', '075293660cfd83e0644b52d5703243cc ', '7c99cda63beb37f8', 'teststudent@whiteboard.org', 1, 'CS', true);
INSERT INTO Users values(NULL,'TestProfessor', '075293660cfd83e0644b52d5703243cc ', '7c99cda63beb37f8', 'testprofessor@whiteboard.org', 2, 'NONE', false);
INSERT INTO Users values(NULL,'TestBoth', '075293660cfd83e0644b52d5703243cc ', '7c99cda63beb37f8', 'testboth@whiteboard.org', 3, 'CS', true);


