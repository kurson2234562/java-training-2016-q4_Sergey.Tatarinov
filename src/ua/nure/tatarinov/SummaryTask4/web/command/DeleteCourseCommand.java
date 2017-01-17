package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.core.Utils;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyCourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;
import ua.nure.tatarinov.SummaryTask4.exception.Messages;

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
        List<CourseDTO> courses = new DerbyCourseDAO().getAllCourses();
        boolean existCourse = false;
        if (!Utils.isDigit(id)){
            request.setAttribute("errorMessage", Messages.ERR_NOT_A_NUMBER);
            return Path.PAGE_ERROR_PAGE;
        } else {
            for (CourseDTO course : courses) {
                if (Integer.parseInt(String.valueOf(request.getParameter("id"))) == course.getIdCourse()) {
                    existCourse = true;
                }
            }
            if (!existCourse) {
                request.setAttribute("errorMessage", Messages.ERR_CANNOT_FIND_COURSE);
                return Path.PAGE_ERROR_PAGE;
            }
        }


        new DerbyCourseDAO().deleteCourseByIdCourse(Integer.parseInt(id));
        return Path.PAGE_ADMIN;
    }
}
