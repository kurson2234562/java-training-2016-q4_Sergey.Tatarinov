package ua.nure.tatarinov.SummaryTask4.db;

/**
 * Holder for fields names of DB tables and beans.
 * @author Sergey Tatarinov
 */
public class Fields {

    public static final String ROLE_ID = "id_role";
    public static final String ROLE_NAME = "name_role";

    public static final String USER_ID = "id_user";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_ROLE_ID = "id_role";

    public static final String THEME_ID = "id_theme";
    public static final String THEME_NAME = "name_theme";

    public static final String STATUS_ID = "id_status";
    public static final String STATUS_NAME = "name_status";

    public static final String LECTURER_ID = "id_lecturer";
    public static final String LECTURER_NAME = "name_lecturer";
    public static final String LECTURER_SURNAME = "surname_lecturer";
    public static final String LECTURER_PATRONYMIC = "patronymic_lecturer";

    public static final String COURSE_ID = "id_course";
    public static final String COURSE_NAME = "name_course";
    public static final String COURSE_DURATION = "duration";
    public static final String COURSE_ID_THEME = "id_theme";
    public static final String COURSE_ID_LECTURER = "id_lecturer";
    public static final String COURSE_ID_STATUS = "id_status";

    public static final String STUDENT_ID = "id_student";
    public static final String STUDENT_SURNAME = "surname_student";
    public static final String STUDENT_NAME = "name_student";
    public static final String STUDENT_PATRONYMIC = "patronymic_student";

    public static final String STUDENT_COURSE_ID_STUDENT_COURSE = "id_student_course";
    public static final String STUDENT_COURSE_ID_STUDENT = "id_student";
    public static final String STUDENT_COURSE_ID_COURSE = "id_course";

    public static final String JOURNAL_ID_STUDENT_COURSE = "id_student_course";
    public static final String JOURNAL_MARK = "mark";

}