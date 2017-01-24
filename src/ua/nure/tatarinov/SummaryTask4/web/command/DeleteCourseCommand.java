package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.core.Utils;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLCourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;
import ua.nure.tatarinov.SummaryTask4.exception.Errors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteCourseCommand extends Command {

    public static final Logger LOG = Logger.getLogger(DeleteCourseCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        List<CourseDTO> courses = new MySQLCourseDAO().getAllCourses();
        boolean existCourse = false;
        if (!Utils.isDigit(id)){
            request.setAttribute("errorMessage", Errors.ERR_NOT_A_NUMBER);
            request.setAttribute("success", false);
            return Path.PAGE_ERROR_PAGE;
        } else {
            for (CourseDTO course : courses) {
                if (Integer.parseInt(String.valueOf(request.getParameter("id"))) == course.getIdCourse()) {
                    request.setAttribute("success", true);
                    existCourse = true;
                }
            }
            if (!existCourse) {
                request.setAttribute("errorMessage", Errors.ERR_CANNOT_FIND_COURSE);
                request.setAttribute("success", false);
                return Path.PAGE_ERROR_PAGE;
            }
        }


        new MySQLCourseDAO().deleteCourseByIdCourse(Integer.parseInt(id));
        return Path.PAGE_ADMIN;
    }
}
