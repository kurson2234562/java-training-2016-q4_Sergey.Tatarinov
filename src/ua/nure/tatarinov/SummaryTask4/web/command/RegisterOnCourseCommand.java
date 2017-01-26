package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLCourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLUserDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Register on course command
 * @author S. Tatarinov
 */
public class RegisterOnCourseCommand extends Command {

    /**
     * Logger for this command
     */
    private static final Logger LOG = Logger.getLogger(RegisterOnCourseCommand.class);

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 8391517597550426880L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing RegisterOnCourseCommand");
        HttpSession session = request.getSession();
        int id = Integer.parseInt(String.valueOf(session.getAttribute("idStudent")));
        int idCourse = Integer.parseInt(request.getParameter("idCourse"));
        new MySQLUserDAO().registerUserOnCourse(id, idCourse);
        List<CourseDTO> courses = new MySQLCourseDAO().findAllCoursesThatUserNotRegistered(id);
        session.setAttribute("coursesForUser", courses);
        return Path.PAGE_STUDENT;
    }
}