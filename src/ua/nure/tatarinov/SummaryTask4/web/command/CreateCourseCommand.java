package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.core.Utils;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.*;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.LecturerDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.StatusDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.ThemeDTO;
import ua.nure.tatarinov.SummaryTask4.exception.Errors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateCourseCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CreateCourseCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing CreateCourseCommand");
        String name = request.getParameter("name");
        String duration = request.getParameter("duration");
        String theme = request.getParameter("theme");
        String lecturer = request.getParameter("lecturer");
        String status = request.getParameter("status");
        boolean existCourse = false;
        boolean existTheme = false;
        boolean existLecturer = false;
        boolean existStatus = false;
        //---------------------------------------------------------------------------------//
        List<CourseDTO> courses = new DerbyCourseDAO().getAllCourses();
        for (CourseDTO course : courses) {
            if (name.equals(course.getNameCourse())) {
                existCourse = true;
            }
        }
        if (existCourse) {
            request.setAttribute("errorMessage", Errors.ERR_COURSE_ALREADY_EXIST);
            return Path.PAGE_ERROR_PAGE;
        } else {
            if (!Utils.isDigit(duration)) {
                request.setAttribute("errorMessage", Errors.ERR_NOT_A_NUMBER);
                return Path.PAGE_ERROR_PAGE;
            } else if (Integer.parseInt(duration) < 0) {
                request.setAttribute("errorMessage", Errors.ERR_NEGATIVE_DURATION);
                return Path.PAGE_ERROR_PAGE;
            }

            if (!Utils.isDigit(theme)) {
                request.setAttribute("errorMessage", Errors.ERR_NOT_A_NUMBER);
                return Path.PAGE_ERROR_PAGE;
            } else {
                List<ThemeDTO> themes = new DerbyThemeDAO().getAllThemes();
                for (ThemeDTO themeDTO : themes) {
                    if (themeDTO.getIdTheme() == Integer.parseInt(theme)) {
                        existTheme = true;
                    }
                }
                if (!existTheme) {
                    request.setAttribute("errorMessage", Errors.ERR_CANNOT_FIND_THEME);
                    return Path.PAGE_ERROR_PAGE;
                }
            }
            if (!Utils.isDigit(lecturer)) {
                request.setAttribute("errorMessage", Errors.ERR_NOT_A_NUMBER);
                return Path.PAGE_ERROR_PAGE;
            } else {
                List<LecturerDTO> lecturers = new DerbyLecturerDAO().getAllLecturers();
                for (LecturerDTO lecturerDTO : lecturers) {
                    if (lecturerDTO.getId() == Integer.parseInt(lecturer)) {
                        existLecturer = true;
                    }
                }
                if (!existLecturer) {
                    request.setAttribute("errorMessage", Errors.ERR_CANNOT_FIND_LECTURER);
                    return Path.PAGE_ERROR_PAGE;
                }
            }
            if (!Utils.isDigit(status)) {
                request.setAttribute("errorMessage", Errors.ERR_NOT_A_NUMBER);
                return Path.PAGE_ERROR_PAGE;
            } else {
                List<StatusDTO> statuses = new DerbyStatusDAO().getAllStatuses();
                for (StatusDTO statusDTO : statuses) {
                    if (statusDTO.getIdStatus() == Integer.parseInt(status)) {
                        existStatus = true;
                    }
                }
                if (!existStatus) {
                    request.setAttribute("errorMessage", Errors.ERR_CANNOT_FIND_STATUS);
                    return Path.PAGE_ERROR_PAGE;
                }
            }
            new DerbyCourseDAO().createCourse(name, Integer.parseInt(duration),
                    Integer.parseInt(theme), Integer.parseInt(lecturer), Integer.parseInt(status));
            return Path.PAGE_ADMIN;
        }
    }
}