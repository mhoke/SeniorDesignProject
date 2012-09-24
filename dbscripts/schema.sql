CREATE CACHED TABLE Assignments(
  id INTEGER IDENTITY, 
  due_date DATE NOT NULL,
course_id INTEGER NOT NULL

 );

CREATE CACHED TABLE Grades (
  id INTEGER IDENTITY, 
  course_id INTEGER NOT NULL, 
  due_date DATE NOT NULL, 
grade_weight_id INTEGER NOT NULL,
grade INTEGER NOT NULL
);

CREATE CACHED TABLE Grade_weights (
	id INTEGER IDENTITY,
  	name VARCHAR(80) NOT NULL,
  	weight INTEGER NOT NULL,
	course_id INTEGER NOT NULL
);

CREATE CACHED TABLE Course (
  	id INTEGER IDENTITY, 
  	name VARCHAR(80) NOT NULL, 
  	time VARCHAR(80) NOT NULL, 
  	course_num INTEGER NOT NULL,
	sec_num INTEGER NOT NULL,
	credits INTEGER NOT NULL,
	weekly_days VARCHAR(80) NOT NULL,
	location VARCHAR(80) NOT NULL,
	crn INTEGER NOT NULL,
	description VARCHAR(80) NOT NULL
);

CREATE CACHED TABLE Enrollment (
 	id INTEGER IDENTITY, 
 	student_id INTEGER NOT NULL, 
	prof_id INTEGER NOT NULL, 
	course_id INTEGER NOT NULL
);

CREATE CACHED TABLE User (
	id INTEGER IDENTITY, 
	username VARCHAR(80) NOT NULL,
	password VARCHAR(32) NOT NULL,
	salt VARCHAR(16) NOT NULL,
	emailAddress VARCHAR(80) NOT NULL,
	userType INTEGER NOT NULL
);