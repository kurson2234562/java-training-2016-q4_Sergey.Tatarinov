package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class CourseCommand extends Command {
    public static final Logger LOG = Logger.getLogger(CourseCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Starting trace CourseCommand");
        HttpSession session = request.getSession();
        String language = String.valueOf(session.getAttribute("language"));
        ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale(language));
        String sort = null;
        String idTheme = null;
        String idLecturer = null;
        if (request.getParameter("idTheme")!=null){
            idTheme = request.getParameter("idTheme");
            if (request.getParameter("idTheme").equals(rb.getString("page.courses.all.themes"))){
                idTheme = null;
            }
            session.setAttribute("idTheme", idTheme);
        }
        if (request.getParameter("idLecturer")!=null) {
            idLecturer = request.getParameter("idLecturer");
            if (request.getParameter("idLecturer").equals(rb.getString("page.courses.all.lecturers"))){
                idLecturer = null;
            }
            session.setAttribute("idLecturer", idLecturer);
        }
        if ((session.getAttribute("sort") != null) && (request.getParameter("sort")!=null)) {
            sort = String.valueOf(session.getAttribute("sort"));
        }
        if (request.getParameter("sort") != null) {
            if (request.getParameter("sort").equals(sort)) {
                if (session.getAttribute("sorting") != null) {
                    if (session.getAttribute("sorting").equals("ASC")) {
                        session.setAttribute("sorting", "DESC");
                        return Path.PAGE_COURSES;
                    } else {
                        session.setAttribute("sorting", "ASC");
                        return Path.PAGE_COURSES;
                    }
                }
            } else {
                session.setAttribute("sorting", "DESC");
                session.setAttribute("sort", request.getParameter("sort"));
                return Path.PAGE_COURSES;
            }
            session.setAttribute("sorting", "DESC");
        }
        return Path.PAGE_COURSES;
    }
}