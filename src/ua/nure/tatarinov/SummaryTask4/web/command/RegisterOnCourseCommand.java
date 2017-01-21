package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyCourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyUserDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RegisterOnCourseCommand extends Command {

    private static final Logger LOG = Logger.getLogger(RegisterOnCourseCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing RegisterOnCourseCommand");
        HttpSession session = request.getSession();
        int id = Integer.parseInt(String.valueOf(session.getAttribute("idStudent")));
        int idCourse = Integer.parseInt(request.getParameter("idCourse"));
        new DerbyUserDAO().registerUserOnCourse(id, idCourse);
        List<CourseDTO> courses = new DerbyCourseDAO().findAllCoursesThatUserNotRegistered(id);
        session.setAttribute("coursesForUser", courses);
        return Path.PAGE_STUDENT;
    }
}