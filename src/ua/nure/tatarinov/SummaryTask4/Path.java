package ua.nure.tatarinov.SummaryTask4;

/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author Sergey Tatarinov
 * 
 */
public final class Path {

    public static final String PAGE_HOME            = "/";
    public static final String PAGE_LOGIN           = "/WEB-INF/jsp/login.jsp";
    public static final String PAGE_FORGET          = "/WEB-INF/jsp/forget.jsp";
    public static final String PAGE_ERROR_PAGE      = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_STUDENT         = "/WEB-INF/jsp/student.jsp";
    public static final String PAGE_ADMIN           = "/WEB-INF/jsp/admin.jsp";
    public static final String PAGE_LECTURER        = "/WEB-INF/jsp/lecturer.jsp";
    public static final String PAGE_COURSES         = "/WEB-INF/jsp/courses.jsp";
    public static final String PAGE_EDIT_JOURNAL    = "/WEB-INF/jsp/editJournal.jsp";
    public static final String PAGE_EDIT_COURSE     = "/WEB-INF/jsp/editCourse.jsp";
    public static final String PAGE_SEARCH          = "/WEB-INF/jsp/search.jsp";

    // commands
    public static final String COMMAND_LIST_ORDERS = "/controller?command=listOrders";
    public static final String COMMAND_LIST_MENU = "/controller?command=listMenu";

}