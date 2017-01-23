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
DROP TABLE STATES;
DROP TABLE ROLES;

CREATE TABLE ROLES(
  id_role INT PRIMARY KEY,
  name_role VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE STATES(
  id_state INT PRIMARY KEY,
  name_state VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE USERS(
  id_user INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  login VARCHAR(30) NOT NULL UNIQUE,
  password VARCHAR (40) NOT NULL,
  email VARCHAR (70),
  id_role INT REFERENCES ROLES(id_role) ON DELETE CASCADE ON UPDATE RESTRICT,
  id_state INT REFERENCES STATES(id_state) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE THEMES(
  id_theme INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  name_theme VARCHAR (30) NOT NULL UNIQUE
);

CREATE TABLE STATUSES(
  id_status INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  name_status VARCHAR (25) NOT NULL UNIQUE
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
  name_course VARCHAR (40) NOT NULL UNIQUE,
  duration INT NOT NULL,
  id_theme INT REFERENCES THEMES(id_theme) ON DELETE CASCADE ON UPDATE RESTRICT,
  id_lecturer INT REFERENCES LECTURERS(id) ON DELETE CASCADE ON UPDATE RESTRICT,
  id_status INT REFERENCES STATUSES(id_status) ON DELETE CASCADE ON UPDATE RESTRICT
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

INSERT INTO STATES VALUES
  (0, 'locked'),
  (1, 'unlocked');

INSERT INTO USERS VALUES
  (DEFAULT, 'root', '63a9f0ea7bb98050796b649e85481845', 'serhii.tatarinov@nure.ua',          0, 1),
  (DEFAULT, 'student', 'cd73502828457d15655bbd7a63fb0bc8', NULL,      1, 1),
  (DEFAULT, 'lecturer', 'b06febcfbc00db4f67aed9234e3e52b0', NULL,     2, 1),
  (DEFAULT, 'admin', '21232f297a57a5a743894a0e4a801fc3', NULL,        0, 1),
  (DEFAULT, 'qwerty', 'd8578edf8458ce06fbc5bb76a58c5ca4', NULL,       1, 1),
  (DEFAULT, 'teststudent', '5d9b4ef4d109e9641fcc7b6396d07d1a', NULL,  1, 1),
  (DEFAULT, 'testlecturer', '6ec781dcf582c92ca02d8e2cfb5a5d9c', NULL, 2, 1),
  (DEFAULT, '123456', 'e10adc3949ba59abbe56e057f20f883e', NULL,       1, 0),
  (DEFAULT, 'temp', '3d801aa532c1cec3ee82d87a99fdf63f', NULL,         2, 1),
  (DEFAULT, 'dmitry', '27183aacdcb689968f322032550ad33d', NULL,       2, 1),
  (DEFAULT, 'qadev', '900f053814787fc81c1a8d410d4d6aa1', NULL,        1, 1),
  (DEFAULT, 'sharpbest', 'ad9f9b39da4641b34aa0f122e9bd7ef1', NULL,    1, 1),
  (DEFAULT, 'jslife', '4763a7704ff43900dc27b260adb31ef0', NULL,       1, 1),
  (DEFAULT, 'football', '37b4e2d82900d5e94b8da524fbeb33c0', NULL,     2, 1),
  (DEFAULT, 'sokol', '897d64b89afcee6b32737e9a1706b7fa', NULL,        1, 1),
  (DEFAULT, 'nika', '15152999e4f8d343989729e38793678e', NULL,         1, 1 );




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
  (DEFAULT, 'Petrov', 'Petr', 'Petrovich', 3),
  (DEFAULT, 'Адаменко', 'Евгений', 'Игоревич', 14);

INSERT INTO ADMINS VALUES
  (DEFAULT, 'Татаринов', 'Сергей', 'Вячеславович', 1),
  (DEFAULT, 'Админов', 'Админ', 'Админович', 4);

INSERT INTO STUDENTS VALUES
  (DEFAULT, 'Шендрик', 'Алексей', '', 11),     --1
  (DEFAULT, 'Pischoha', 'Konstantin', 'Sergeevich', 2),--2
  (DEFAULT, 'Shylo', 'Rodion', '', 6),--3
  (DEFAULT, 'Radchenko', 'Vadim', '', 5),--4
  (DEFAULT, 'Serenko', 'Ivan', '', 8),--5
  (DEFAULT, 'Мирошниченко', 'Егор', '', 12),--6
  (DEFAULT, 'Кругликов', 'Данил', '', 13),--7
  (DEFAULT, 'Соколов', 'Дмитрий', 'Сергеевич', 15),--8
  (DEFAULT, 'Перцева', 'Вероника', 'Ильинична', 16);--9

/*id_course name_course duration id_theme id_lecutrer id_status*/
INSERT INTO COURSES VALUES
  (DEFAULT, 'Java Web developing'  , 14, 1, 1, 1),
  (DEFAULT, 'C# developing'        , 17, 2, 2, 1),
  (DEFAULT, 'ASP.Net'              , 15, 2, 3, 2),
  (DEFAULT, 'JavaScript developing', 16, 3, 3, 3),
  (DEFAULT, 'HTML+CSS developing'  , 16, 3, 3, 1),
  (DEFAULT, 'Python developing'    , 20, 3, 2, 4),
  (DEFAULT, 'QA testing'           , 10, 3, 1, 4),
  (DEFAULT, 'PHP developing'       , 14, 3, 1, 3),
  (DEFAULT, 'Kotlin developing'    ,  7, 1, 2, 1),
  (DEFAULT, 'Groovy developing'    ,  9, 1, 2, 1),
  (DEFAULT, 'Scala developing'     , 12, 1, 5, 2),
  (DEFAULT, 'Ruby developing'      , 13, 3, 5, 1);

--9--12
INSERT INTO STUDENT_COURSE VALUES
  (DEFAULT, 2,  1),--1
  (DEFAULT, 3,  1),--2
  (DEFAULT, 5,  1),--3
  (DEFAULT, 1,  6),--4
  (DEFAULT, 4,  3),--5
  (DEFAULT, 6,  2),--6
  (DEFAULT, 7,  4),--7
  (DEFAULT, 2,  2),--8
  (DEFAULT, 2,  6),--9
  (DEFAULT, 2,  3),--10
  (DEFAULT, 2,  7),--11
  (DEFAULT, 1,  7),--12
  (DEFAULT, 6,  7),--13
  (DEFAULT, 1, 11),--14
  (DEFAULT, 1, 12),--15
  (DEFAULT, 7,  1),--16
  (DEFAULT, 8,  5),--17
  (DEFAULT, 9,  2),--18
  (DEFAULT, 9,  7),--19
  (DEFAULT, 9,  3),--20
  (DEFAULT, 9,  5),--21
  (DEFAULT, 3,  8),--22
  (DEFAULT, 4,  9),--23
  (DEFAULT, 5, 10),--24
  (DEFAULT, 8, 11),--25
  (DEFAULT, 7, 12),--26
  (DEFAULT, 2, 11),--27
  (DEFAULT, 4, 12);--28

INSERT INTO JOURNAL VALUES
  (4, 75),
  (9, 90),
  (11, 80),
  (12, 95),
  (13, 70),
  (15, 85);