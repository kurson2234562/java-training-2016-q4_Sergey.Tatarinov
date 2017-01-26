package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.core.Utils;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLCourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;
import ua.nure.tatarinov.SummaryTask4.exception.Errors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Update course button command
 * @author S. Tatarinov
 */
public class UpdateCourseButtonCommand extends Command {

    /**
     * Logger for this command
     */
    public static final Logger LOG = Logger.getLogger(UpdateCourseButtonCommand.class);

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 5831274963843382699L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Starting trace UpdateCourseButtonCommand");
        HttpSession session = request.getSession();
        String test = request.getParameter("id");
        List<CourseDTO> courses = new MySQLCourseDAO().getAllCourses();
        boolean existCourse = false;
        if (!Utils.isNumber(test)){
            request.setAttribute("errorMessage", Errors.ERR_NOT_A_NUMBER);
            return Path.PAGE_ERROR_PAGE;
        }else {
            for (CourseDTO course : courses) {
                if (Integer.parseInt(String.valueOf(request.getParameter("id"))) == course.getIdCourse()) {
                    existCourse = true;
                }
            }
            if (!existCourse) {
                request.setAttribute("errorMessage", Errors.ERR_CANNOT_FIND_COURSE);
                return Path.PAGE_ERROR_PAGE;
            }
        }
        session.setAttribute("updatecourseid", test);
        if (test == null) {
            request.setAttribute("errorMessage", Errors.ERR_CANNOT_OBTAIN_COURSE_ID);
            return Path.PAGE_ERROR_PAGE;
        }
        return Path.PAGE_EDIT_COURSE;
    }
}