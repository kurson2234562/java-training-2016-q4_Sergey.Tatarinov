package ua.nure.tatarinov.SummaryTask4.db;

public class Query {

    public static final String SELECT_USER_AS_STUDENT = "SELECT * FROM USERS INNER JOIN STUDENTS ON STUDENTS.ID_USER = USERS.ID_USER WHERE LOGIN=?";
    public static final String SELECT_USER_AS_LECTURER ="SELECT * FROM USERS INNER JOIN LECTURERS ON LECTURERS.ID_USER = USERS.ID_USER WHERE LOGIN=?";
    public static final String SELECT_USER_AS_ADMIN =   "SELECT * FROM USERS INNER JOIN ADMINS ON ADMINS.ID_USER = USERS.ID_USER WHERE LOGIN=?";

    public static final String SELECT_ALL_THEMES =          "SELECT * FROM THEMES";
    public static final String SELECT_ALL_LECTURERS =       "SELECT * FROM LECTURERS";
    public static final String SELECT_ALL_STATUSES =        "SELECT * FROM STATUSES";
    public static final String SELECT_ALL_COURSES =         "SELECT * FROM COURSES";
    public static final String SELECT_ALL_STUDENTS =        "SELECT * FROM STUDENTS";
    public static final String SELECT_ALL_STUDENT_ON_COURSE="SELECT * FROM STUDENT_COURSE";
    public static final String SELECT_ALL_DEFINITE_COURSE = "SELECT * FROM COURSES WHERE ID_COURSE=?";

    public static final String SELECT_PROGRESS_BY_USER = "select name_course, LECTURERS.surname, LECTURERS.name, LECTURERS.patronymic, mark from STUDENTS INNER JOIN STUDENT_COURSE ON STUDENTS.id = STUDENT_COURSE.id_student INNER JOIN COURSES ON STUDENT_COURSE.id_course = COURSES.id_course INNER JOIN STATUSES ON COURSES.id_status = STATUSES.id_status INNER JOIN JOURNAL ON STUDENT_COURSE.id_student_course = JOURNAL.id_student_course INNER JOIN LECTURERS ON COURSES.id_lecturer = LECTURERS.id INNER JOIN USERS ON STUDENTS.id_user = USERS.id_user WHERE STATUSES.id_status = 4 and USERS.login=?";
    public static final String SELECT_INFO_ABOUT_COURSE_BY_LOGIN_AND_BY_COURSE_STATUS = "select NAME_COURSE, DURATION, THEMES.name_theme, LECTURERS.surname, LECTURERS.name, LECTURERS.patronymic from students inner join student_course on student_course.id_student=students.id inner join courses on student_course.id_course=courses.id_course inner join statuses on statuses.id_status = courses.id_status inner join themes on COURSES.id_theme = THEMES.id_theme inner join LECTURERS ON COURSES.id_lecturer = LECTURERS.id inner join users ON users.id_user = students.id_user where users.login = ? AND STATUSES.name_status = ?";
    public static final String SELECT_INFO_ABOUT_STUDENTS_FROM_LECTURER = "SELECT JOURNAL.id_student_course, STUDENTS.id, login, name_course, duration, STUDENTS.name, STUDENTS.surname, STUDENTS.patronymic, mark FROM LECTURERS INNER JOIN USERS ON LECTURERS.id_user = USERS.id_user INNER JOIN COURSES ON LECTURERS.id = COURSES.id_lecturer INNER JOIN STUDENT_COURSE ON COURSES.id_course = STUDENT_COURSE.id_course INNER JOIN JOURNAL ON STUDENT_COURSE.id_student_course = JOURNAL.id_student_course INNER JOIN STUDENTS ON STUDENT_COURSE.id_student = STUDENTS.id WHERE login = ?";
    public static final String SELECT_STUDENT_FROM_JOURNAL = "SELECT surname, name, patronymic, mark FROM JOURNAL INNER JOIN STUDENT_COURSE ON STUDENT_COURSE.id_student_course = JOURNAL.id_student_course INNER JOIN STUDENTS ON STUDENTS.ID=STUDENT_COURSE.ID_STUDENT WHERE JOURNAL.ID_STUDENT_COURSE=?";
    public static final String SELECT_ALL_INFO_ABOUT_COURSES = "SELECT NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, count(id_student_course) as COUNT FROM COURSES INNER JOIN THEMES ON THEMES.ID_THEME=COURSES.ID_THEME INNER JOIN LECTURERS ON LECTURERS.ID=COURSES.ID_LECTURER  INNER JOIN STATUSES ON STATUSES.ID_STATUS=COURSES.ID_STATUS INNER JOIN STUDENT_COURSE ON COURSES.id_course = STUDENT_COURSE.id_course GROUP BY NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS\n";
    public static final String SELECT_STUDENTS_AND_STATES = "SELECT USERS.ID_USER, SURNAME, NAME, PATRONYMIC, LOGIN, PASSWORD, NAME_STATE FROM STUDENTS INNER JOIN USERS ON STUDENTS.id_user = USERS.id_user INNER JOIN STATES ON USERS.id_state = STATES.id_state";
    public static final String SELECT_LAST_USER_ID = "SELECT MAX(ID_USER) FROM USERS";
    public static final String SELECT_LAST_LECTURER_ID = "SELECT MAX(ID) FROM LECTURERS";

    public static final String SELECT_COUNT_STUDENTS_PER_COURSE = "SELECT COUNT(ID_STUDENT_COURSE), ID_COURSE FROM STUDENT_COURSE GROUP BY ID_COURSE";

    public static final String CREATE_USER =        "INSERT INTO USERS VALUES       (DEFAULT, ?, ?, 2, 1)";
    public static final String CREATE_LECTURER =    "INSERT INTO LECTURERS VALUES   (DEFAULT, ?, ?, ?, ?)";
    public static final String CREATE_COURSE =      "INSERT INTO COURSES VALUES     (DEFAULT, ?, ?, ?, ?, ?)";

    public static final String CREATE_MARK_FOR_STUDENT = "INSERT INTO JOURNAL VALUES(?, ?)";

    public static final String UPDATE_JOURNAL =     "UPDATE JOURNAL SET MARK = ? WHERE ID_STUDENT_COURSE = ?";
    public static final String UPDATE_COURSE =      "UPDATE COURSES SET NAME_COURSE=?, DURATION=?, ID_THEME=?,ID_LECTURER=?,ID_STATUS=? WHERE ID_COURSE=?";
    public static final String CHANGE_STATE_USER =  "UPDATE USERS SET ID_STATE = ? WHERE ID_USER = ?";
    public static final String CHANGE_LECTURER =    "UPDATE COURSES SET ID_LECTURER = ? WHERE ID_COURSE = ?";

    public static final String DELETE_COURSE =      "DELETE FROM COURSES WHERE ID_COURSE=?";
}
