-- noinspection SqlDialectInspectionForFile

DROP TABLE JOURNAL;
DROP TABLE STUDENT_COURSE;
DROP TABLE STUDENTS;
DROP TABLE COURSES;
DROP TABLE ADMINS;
DROP TABLE LECTURERS;
DROP TABLE STATUSES;
DROP TABLE THEMES;
DROP TABLE USERS;
DROP TABLE ROLES;

CREATE TABLE ROLES(
  id_role INT PRIMARY KEY,
  name_role VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE USERS(
  id_user INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  login VARCHAR(30) NOT NULL,
  password VARCHAR (40) NOT NULL,
  id_role INT REFERENCES ROLES(id_role) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE THEMES(
  id_theme INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  name_theme VARCHAR (30) NOT NULL
);

CREATE TABLE STATUSES(
  idStatus INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  nameStatus VARCHAR (25) NOT NULL
);

CREATE TABLE LECTURERS(
  id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  surname VARCHAR(30) NOT NULL,
  name VARCHAR (30) NOT NULL,
  patronymic VARCHAR (20),
  id_user INT REFERENCES USERS(id_user) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE ADMINS(
  id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  surname VARCHAR(30) NOT NULL,
  name VARCHAR (30) NOT NULL,
  patronymic VARCHAR (20),
  id_user INT REFERENCES USERS(id_user) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE STUDENTS(
  id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  surname VARCHAR(30) NOT NULL,
  name VARCHAR (30) NOT NULL,
  patronymic VARCHAR (20),
  id_user INT REFERENCES USERS(id_user) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE COURSES(
  id_course INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  name_course VARCHAR (40) NOT NULL,
  duration INT NOT NULL,
  id_theme INT REFERENCES THEMES(id_theme) ON DELETE CASCADE ON UPDATE RESTRICT,
  id_lecturer INT REFERENCES LECTURERS(id) ON DELETE CASCADE ON UPDATE RESTRICT,
  idStatus INT REFERENCES STATUSES(idStatus) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE STUDENT_COURSE(
  id_student_course INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  id_student INT REFERENCES STUDENTS(id) ON DELETE CASCADE ON UPDATE RESTRICT,
  id_course INT REFERENCES COURSES(id_course) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE JOURNAL(
  id_student_course INT PRIMARY KEY REFERENCES STUDENT_COURSE(id_student_course) ON DELETE CASCADE ON UPDATE RESTRICT,
  mark INT NOT NULL
);


/*==================================================INSERT QUERIES==================================================*/


INSERT INTO ROLES VALUES
  (0, 'Admin'),
  (1, 'Student'),
  (2, 'Lecturer');

INSERT INTO USERS VALUES
  (DEFAULT, 'root', 'root', 0),
  (DEFAULT, 'student', 'student', 1),
  (DEFAULT, 'lecturer', 'lecturer', 2),
  (DEFAULT, 'admin', 'admin', 0),
  (DEFAULT, 'qwerty', 'qwerty', 1),
  (DEFAULT, 'teststudent', 'teststudent', 1),
  (DEFAULT, 'testlecturer', 'testlecturer', 2),
  (DEFAULT, '123456', '123456', 1),
  (DEFAULT, 'temp', 'temp', 2),
  (DEFAULT, 'dmitry', 'dmitry', 2),
  (DEFAULT, 'qadev', 'qadev', 1),
  (DEFAULT, 'sharpbest', 'sharpbest', 1),
  (DEFAULT, 'jslife', 'jslife', 1);


INSERT INTO STATUSES VALUES
  (DEFAULT, 'Opened'),
  (DEFAULT, 'In progress'),
  (DEFAULT, 'Closed'),
  (DEFAULT, 'Finished');

INSERT INTO THEMES VALUES
  (DEFAULT, 'Java developing'),
  (DEFAULT, '.Net developing'),
  (DEFAULT, 'Web developing');

INSERT INTO LECTURERS VALUES
  (DEFAULT, 'Kolesnikov','Dmitry','Olegovich', 10),
  (DEFAULT, 'Мищеряков','Юрий', 'Валентинович', 9),
  (DEFAULT, 'Ivanov', 'Ivan', 'Ivanovich', 7),
  (DEFAULT, 'Petrov', 'Petr', 'Petrovich', 3);

INSERT INTO ADMINS VALUES
  (DEFAULT, 'Татаринов', 'Сергей', 'Вячеславович', 1),
  (DEFAULT, 'Админов', 'Админ', 'Админович', 4);

INSERT INTO STUDENTS(id, surname, name, id_user) VALUES
  (DEFAULT, 'Шендрик', 'Алексей', 11),
  (DEFAULT, 'Pischoha', 'Konstantin', 2),
  (DEFAULT, 'Shylo', 'Rodion', 6),
  (DEFAULT, 'Radchenko', 'Vadim', 5),
  (DEFAULT, 'Serenko', 'Ivan', 8),
  (DEFAULT, 'Мирошниченко', 'Егор', 12),
  (DEFAULT, 'Кругликов', 'Данил', 13);

INSERT INTO COURSES VALUES
  (DEFAULT, 'Java Web developing', 14, 1, 1, 1),
  (DEFAULT, 'C# developing', 17, 2, 2, 1),
  (DEFAULT, 'ASP.Net', 15, 2, 3, 2),
  (DEFAULT, 'JS developing', 16, 3, 3, 3),
  (DEFAULT, 'HTML+CSS developing', 16, 3, 3, 2),
  (DEFAULT, 'Python developing', 20, 3, 2, 4);

INSERT INTO STUDENT_COURSE VALUES
  (DEFAULT, 2, 1),
  (DEFAULT, 3, 1),
  (DEFAULT, 5, 1),
  (DEFAULT, 1, 6),
  (DEFAULT, 4, 3),
  (DEFAULT, 6, 2),
  (DEFAULT, 7, 4),
  (DEFAULT, 2, 2),
  (DEFAULT, 2, 6),
  (DEFAULT, 2, 3);

INSERT INTO JOURNAL VALUES
  (1, 90),
  (2, 85),
  (3, 90),
  (4, 75),
  (5, 60);

select STUDENTS.SURNAME, STUDENTS.NAME, STUDENTS.PATRONYMIC, NAME_COURSE, DURATION, THEMES.name_theme, LECTURERS.surname, LECTURERS.name, LECTURERS.patronymic
from students
  inner join student_course on student_course.id_student=students.id
  inner join courses on student_course.id_course=courses.id_course
  inner join statuses on statuses.idStatus = courses.idStatus
  inner join themes on COURSES.id_theme = THEMES.id_theme
  inner join LECTURERS ON COURSES.id_lecturer = LECTURERS.id
  where STUDENTS.ID = 2 AND STATUSES.idStatus = 2;