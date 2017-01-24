package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLCourseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateCourseCommand extends Command {

    private static final Logger LOG = Logger.getLogger(UpdateCourseCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing UpdateCourseCommand");
        HttpSession session = request.getSession();
        int id;
        String newName = request.getParameter("name");
        int newDuration = Integer.parseInt(request.getParameter("duration"));
        int newTheme = Integer.parseInt(request.getParameter("theme"));
        int newLecturer = Integer.parseInt(request.getParameter("lecturer"));
        int newStatus = Integer.parseInt(request.getParameter("status"));
        id = Integer.parseInt(String.valueOf(session.getAttribute("updatecourseid")));
        new MySQLCourseDAO().updateCourse(newName, newDuration, newTheme, newLecturer, newStatus, id);
        return Path.PAGE_ADMIN;
    }
}