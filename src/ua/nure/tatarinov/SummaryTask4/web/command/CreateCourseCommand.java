package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyCourseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCourseCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CreateCourseCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing CreateCourseCommand");
        String name = request.getParameter("name");
        int duration = Integer.parseInt(request.getParameter("duration"));
        int theme = Integer.parseInt(request.getParameter("theme"));
        int lecturer = Integer.parseInt(request.getParameter("lecturer"));
        int status = Integer.parseInt(request.getParameter("status"));
        new DerbyCourseDAO().createCourse(name, duration, theme, lecturer, status);
        return Path.PAGE_ADMIN;
    }
}
