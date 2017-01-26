package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLCourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLLecturerDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLStudentDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.LecturerDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.StudentDTO;
import ua.nure.tatarinov.SummaryTask4.exception.Errors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Search command
 * @author S. Tatarinov
 */
public class SearchCommand extends Command {

    /**
     * Logger for this command
     */
    private static final Logger LOG = Logger.getLogger(SearchCommand.class);

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -5598542401929015124L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing SearchCommand");
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            String search = new String(request.getParameter("search").getBytes("ISO-8859-1"), "UTF-8");
            if ((!search.contains("%")) && (!search.equals("%")) && (!search.equals("_")) && (!search.contains("_"))) {
                List<StudentDTO> students = null;
                List<LecturerDTO> lecturers = null;
                List<CourseDTO> courses = null;
                int size = 0;
                if ((!search.isEmpty()) || (search.equals(null))) {
                    students = new MySQLStudentDAO().findStudentsByString(search);
                    lecturers = new MySQLLecturerDAO().findLecturersByString(search);
                    courses = new MySQLCourseDAO().findCourseByString(search);
                    request.setAttribute("students", students);
                    request.setAttribute("lecturers", lecturers);
                    request.setAttribute("courses", courses);
                    request.setAttribute("search", search);
                }
                if (students == null || lecturers == null || courses == null) {
                    size = 0;
                } else {
                    size = students.size() + lecturers.size() + courses.size();
                }
                request.setAttribute("size", size);
            }
        } else {
            request.setAttribute("errorMessage", Errors.ERR_USER_NOT_LOGGER);
        }
        return Path.PAGE_SEARCH;
    }
}