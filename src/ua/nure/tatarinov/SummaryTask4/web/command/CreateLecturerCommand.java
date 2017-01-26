package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLCourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLLecturerDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLUserDAO;
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

    /**
     * Logger for this command
     */
    private static final Logger LOG = Logger.getLogger(CreateLecturerCommand.class);

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -3234967330527893569L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing CreateLecturerCommand");
        HttpSession session = request.getSession();
        int id = -1;
        String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
        String surname = new String(request.getParameter("surname").getBytes("ISO-8859-1"), "UTF-8");
        String patronymic = new String(request.getParameter("patronymic").getBytes("ISO-8859-1"), "UTF-8");
        String login = new String(request.getParameter("login").getBytes("ISO-8859-1"), "UTF-8");
        String password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");
        String confirm = new String(request.getParameter("confirm").getBytes("ISO-8859-1"), "UTF-8");
        boolean existCourse = false;
        int idCourse = Integer.parseInt(request.getParameter("idCourse"));
        if (!confirm.equals(password)) {
            request.setAttribute("errorMessage", Errors.ERR_PASS_NO_MATCH);
            return Path.PAGE_ERROR_PAGE;
        } else {
            List<UserDTO> users = new MySQLUserDAO().getAllUsers();
            for (UserDTO user : users) {
                if (user.getLogin().equals(login)) {
                    request.setAttribute("errorMessage", Errors.ERR_USERNAME_ALREADY_EXIST);
                    return Path.PAGE_ERROR_PAGE;
                }
            }
            List<CourseDTO> courses = new MySQLCourseDAO().getAllCourses();
            for (CourseDTO course : courses) {
                if (course.getIdCourse() == idCourse) {
                    existCourse = true;
                }
            }
            if (!existCourse) {
                request.setAttribute("errorMessage", Errors.ERR_CANNOT_FIND_COURSE);
                return Path.PAGE_ERROR_PAGE;
            }
        }
        UserDTO user = new MySQLUserDAO().createUser(login, password);
        id = new MySQLLecturerDAO().createLecturer(surname, name, patronymic, user.getIdUser());
        if (id != -1) {
            new MySQLLecturerDAO().changeLecturer(id, idCourse);
        }
        return Path.PAGE_COURSES;
    }
}