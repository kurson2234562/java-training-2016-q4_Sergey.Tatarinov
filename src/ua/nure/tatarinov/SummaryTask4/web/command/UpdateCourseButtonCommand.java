package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.exception.Messages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateCourseButtonCommand extends Command {

    public static final Logger LOG = Logger.getLogger(UpdateCourseButtonCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Starting trace UpdateCourseButtonCommand");
        HttpSession session = request.getSession();
        session.setAttribute("updatecourseid", request.getParameter("id"));
        if (request.getParameter("id") == null) {
            request.setAttribute("errorMessage", Messages.ERR_CANNOT_OBTAIN_COURSE_ID);
            return Path.PAGE_ERROR_PAGE;
        }
        return Path.PAGE_EDIT_COURSE;
    }
}