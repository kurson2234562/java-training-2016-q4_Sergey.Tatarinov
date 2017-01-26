package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLCourseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Update course command
 * @author S. Tatarinov
 */
public class UpdateCourseCommand extends Command {

    /**
     * Logger for this command
     */
    private static final Logger LOG = Logger.getLogger(UpdateCourseCommand.class);

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 8808315569920469789L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing UpdateCourseCommand");
        HttpSession session = request.getSession();
        int id;
        String newName = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
        int newDuration = Integer.parseInt(request.getParameter("duration"));
        int newTheme = Integer.parseInt(request.getParameter("theme"));
        int newLecturer = Integer.parseInt(request.getParameter("lecturer"));
        int newStatus = Integer.parseInt(request.getParameter("status"));
        id = Integer.parseInt(String.valueOf(session.getAttribute("updatecourseid")));
        new MySQLCourseDAO().updateCourseById(newName, newDuration, newTheme, newLecturer, newStatus, id);
        return Path.PAGE_ADMIN;
    }
}