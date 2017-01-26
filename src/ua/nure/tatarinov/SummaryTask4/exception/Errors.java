package ua.nure.tatarinov.SummaryTask4.exception;

/**
 * Contains all errors constants
 */
public class Errors {

    /**
     * Default constructor of Errors
     */
    private Errors(){
    }

    public static final String ERR_CANNOT_OBTAIN_COURSE_ID = "Cannot obtain a course id";
    public static final String ERR_NOT_A_NUMBER = "The input string is not a number";
    public static final String ERR_ABOVE_THE_LIMIT = "The entered number above the permissible limit";
    public static final String ERR_BELOW_THE_LIMIT = "The entered numbers below the limit";
    public static final String ERR_CANNOT_FIND_LECTURER = "Can't find a lecturer with the same name";
    public static final String ERR_CANNOT_FIND_THEME = "Can't find the theme with the same name";
    public static final String ERR_CANNOT_FIND_FIELD = "Can't find the field with the same name";
    public static final String ERR_CANNOT_FIND_STATUS = "Can't find the status with the same name";
    public static final String ERR_CANNOT_FIND_STUDENT = "Can't find a student with such parameters";
    public static final String ERR_CANNOT_FIND_COURSE = "Can't find a course with such parameters";
    public static final String ERR_INVALID_VALUE_LOCK = "Invalid value for lock";
    public static final String ERR_INVALID_VALUE_LANGUAGE = "Invalid value for language";
    public static final String ERR_INVALID_PASSWORD = "Invalid password, please try again";
    public static final String ERR_CANNOT_FIND_USER_NAME="Can't find a user with the same name";
    public static final String ERR_LOCKED = "You are locked.";
    public static final String ERR_PASS_NO_MATCH = "Passwords do not match";
    public static final String ERR_USERNAME_ALREADY_EXIST = "User with this login already exists";
    public static final String ERR_COURSE_ALREADY_EXIST = "Course with this name already exists";
    public static final String ERR_NEGATIVE_DURATION = "The duration of the courses can not be negative";
    public static final String ERR_NOT_FIND_USER = "There is no user with that name or email";
    public static final String ERR_USER_HAS_NO_EMAIL ="This user has no e-mail address";
    public static final String ERR_USER_NOT_LOGGER = "You are not logged. Please, login.";
    public static final String ERR_BAD_REQUEST = "Bad request.";
    public static final String ERR_DONT_ENTER_SUFFICIENT_PARAMETERS= "You did not enter a sufficient number of parameters";

}
