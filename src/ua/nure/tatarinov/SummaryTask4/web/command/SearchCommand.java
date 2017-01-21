package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyCourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyLecturerDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyStudentDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.LecturerDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.StudentDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchCommand extends Command {

    private static final Logger LOG = Logger.getLogger(SearchCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing SearchCommand");
        HttpSession session = request.getSession();
        String search = request.getParameter("search");
        List<StudentDTO> students = null;
        List<LecturerDTO> lecturers = null;
        List<CourseDTO> courses = null;
        int size = 0;
        if ((!search.isEmpty()) || (search.equals(null))) {
            students = new DerbyStudentDAO().findStudentsByString(search);
            lecturers = new DerbyLecturerDAO().findLecturersByString(search);
            courses = new DerbyCourseDAO().findCourseByString(search);
            request.setAttribute("students", students);
            request.setAttribute("lecturers", lecturers);
            request.setAttribute("courses", courses);
            request.setAttribute("search", search);
        }
        if (students==null || lecturers==null || courses==null) {
            size = 0;
        } else {
            size = students.size() + lecturers.size() + courses.size();
        }
        request.setAttribute("size", size);
        return Path.PAGE_SEARCH;
    }
}