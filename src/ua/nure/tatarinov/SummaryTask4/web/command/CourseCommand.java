package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.core.Utils;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyLecturerDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyThemeDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.LecturerDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.ThemeDTO;
import ua.nure.tatarinov.SummaryTask4.exception.Errors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CourseCommand extends Command {
    public static final Logger LOG = Logger.getLogger(CourseCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Starting trace CourseCommand");
        List<LecturerDTO> lecturers = new DerbyLecturerDAO().getAllLecturers();
        List<ThemeDTO> themes = new DerbyThemeDAO().getAllThemes();
        List<String> fields = Arrays.asList("name_course", "duration", "name_theme", "surname", "name", "patronymic", "count", "name_status");
        HttpSession session = request.getSession();
        String language = String.valueOf(session.getAttribute("language"));
        ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale(language));
        int state = Integer.parseInt(String.valueOf(session.getAttribute("state")));
        boolean existLecturer = false;
        boolean existTheme = false;
        boolean existField = false;
        String sort = null;
        String idTheme = null;
        String idLecturer = null;
        String forward = Path.PAGE_COURSES;
        if (state == 0) {
            request.setAttribute("errorMessage", Errors.ERR_LOCKED);
            forward = Path.PAGE_ERROR_PAGE;
        }
        if (request.getParameter("idTheme") != null) {
            idTheme = request.getParameter("idTheme");
            if (request.getParameter("idTheme").equals(rb.getString("page.courses.all.themes"))) {
                idTheme = null;
            } else {
                if (Utils.isDigit(idTheme)) {
                    for (ThemeDTO theme : themes) {
                        if (theme.getIdTheme() == Integer.parseInt(idTheme)) {
                            existTheme = true;
                        }
                    }
                    if (!existTheme) {
                        LOG.error(Errors.ERR_CANNOT_FIND_THEME);
                        request.setAttribute("errorMessage", Errors.ERR_CANNOT_FIND_THEME);
                        forward = Path.PAGE_ERROR_PAGE;
                    }
                } else {
                    LOG.error(Errors.ERR_CANNOT_FIND_THEME);
                    request.setAttribute("errorMessage", Errors.ERR_CANNOT_FIND_THEME);
                    return Path.PAGE_ERROR_PAGE;
                }
            }
            session.setAttribute("idTheme", idTheme);
        }
        if (request.getParameter("idLecturer") != null) {
            idLecturer = request.getParameter("idLecturer");
            if (request.getParameter("idLecturer").equals(rb.getString("page.courses.all.lecturers"))) {
                idLecturer = null;
            } else {
                if (Utils.isDigit(idLecturer)) {
                    for (LecturerDTO lecturer : lecturers) {
                        if (lecturer.getId() == Integer.parseInt(idLecturer)) {
                            existLecturer = true;
                        }
                    }
                    if (!existLecturer) {
                        LOG.error(Errors.ERR_CANNOT_FIND_LECTURER);
                        request.setAttribute("errorMessage", Errors.ERR_CANNOT_FIND_LECTURER);
                        forward = Path.PAGE_ERROR_PAGE;
                    }
                } else {
                    LOG.error(Errors.ERR_CANNOT_FIND_LECTURER);
                    request.setAttribute("errorMessage", Errors.ERR_CANNOT_FIND_LECTURER);
                    return Path.PAGE_ERROR_PAGE;
                }
            }
            session.setAttribute("idLecturer", idLecturer);
        }
        if ((session.getAttribute("sort") != null) && (request.getParameter("sort") != null)) {
            sort = String.valueOf(session.getAttribute("sort"));
        }
        if (request.getParameter("sort") != null) {
            if (request.getParameter("sort").equals(sort)) {
                if (session.getAttribute("sorting") != null) {
                    if (session.getAttribute("sorting").equals("ASC")) {
                        session.setAttribute("sorting", "DESC");
                        forward = Path.PAGE_COURSES;
                    } else {
                        session.setAttribute("sorting", "ASC");
                        forward = Path.PAGE_COURSES;
                    }
                }
            } else {
                session.setAttribute("sorting", "DESC");
                forward = Path.PAGE_COURSES;
                for (String field : fields) {
                    if (field.equals(request.getParameter("sort"))) {
                        existField = true;
                        session.setAttribute("sort", request.getParameter("sort"));
                    }
                }
                if (!existField) {
                    LOG.error(Errors.ERR_CANNOT_FIND_FIELD);
                    request.setAttribute("errorMessage", Errors.ERR_CANNOT_FIND_FIELD);
                    forward = Path.PAGE_ERROR_PAGE;
                }
            }
        }
        return forward;
    }
}