package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyCourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyLecturerDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyUserDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.UserDTO;
import ua.nure.tatarinov.SummaryTask4.exception.Errors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CreateLecturerCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CreateLecturerCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing CreateLecturerCommand");
        HttpSession session = request.getSession();
        int id = -1;
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        boolean existCourse = false;
        int idCourse = Integer.parseInt(request.getParameter("idCourse"));
        if (!confirm.equals(password)){
            request.setAttribute("errorMessage", Errors.ERR_PASS_NO_MATCH);
            return Path.PAGE_ERROR_PAGE;
        }else {
            List<UserDTO> users = new DerbyUserDAO().getAllUsers();
            for (UserDTO user : users) {
                if (user.getLogin().equals(login)) {
                    request.setAttribute("errorMessage", Errors.ERR_USERNAME_ALREADY_EXIST);
                    return Path.PAGE_ERROR_PAGE;
                }
            }
            List<CourseDTO> courses = new DerbyCourseDAO().getAllCourses();
            for (CourseDTO course : courses) {
                if (course.getIdCourse() == idCourse) {
                    existCourse = true;
                }
            }
            if (!existCourse){
                request.setAttribute("errorMessage", Errors.ERR_CANNOT_FIND_COURSE);
                return Path.PAGE_ERROR_PAGE;
            }
        }
        UserDTO user = new DerbyUserDAO().createUser(login, password);
        id = new DerbyLecturerDAO().createLecturer(surname, name, patronymic, user.getIdUser());
        if (id != -1) {
            new DerbyLecturerDAO().changeLecturer(id, idCourse);
        }
        return Path.PAGE_COURSES;
    }
}