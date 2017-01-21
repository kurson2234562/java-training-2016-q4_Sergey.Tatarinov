package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.core.Utils;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.*;
import ua.nure.tatarinov.SummaryTask4.db.dto.*;
import ua.nure.tatarinov.SummaryTask4.exception.Errors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginCommand")
public class LoginCommand extends Command {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing LoginCommand");
        String forward = "";
        HttpSession session = request.getSession();
        String login = "", password = "";
        if ((request.getParameter("username") != null) && (request.getParameter("password") != null)) {
            login = request.getParameter("username");
            password = request.getParameter("password");
        } else if ((session.getAttribute("username") != null) && (session.getAttribute("password") != null)) {
            login = String.valueOf(session.getAttribute("username"));
            password = String.valueOf(session.getAttribute("password"));
        }
        UserDTO user = null;
        String role = null;
        String state = null;
        StudentDTO student = null;
        List<CourseDTO> coursesForUser = null;
        LOG.info("UserDTO " + login + " logged");
        if ((!login.isEmpty()) && (!password.isEmpty())) {
            user = new DerbyUserDAO().findUserByLogin(login);
            if (user != null) {
                if (user.getPassword().equals(Utils.encrypt(password))) {
                    switch (user.getRoleId()) {
                        case 0:
                            role = "admin";
                            break;
                        case 1:
                            role = "student";
                            break;
                        case 2:
                            role = "lecturer";
                            break;
                    }
                    String stateId = String.valueOf(user.getStateId());
                    request.setAttribute("username", login);
                    request.setAttribute("role", role);
                    request.setAttribute("password", password);
                    session.setAttribute("state", stateId);
                    session.setAttribute("username", login);
                    session.setAttribute("role", role);
                    session.setAttribute("password", password);
                } else {
                    request.setAttribute("errorMessage", Errors.ERR_INVALID_PASSWORD);
                    return Path.PAGE_ERROR_PAGE;
                }
            } else {
                request.setAttribute("errorMessage", Errors.ERR_CANNOT_FIND_USER_NAME);
                return Path.PAGE_ERROR_PAGE;
            }
            session.setAttribute("user", user);
            if (user.getStateId() == 0) {
                request.setAttribute("errorMessage", Errors.ERR_LOCKED);
                return Path.PAGE_ERROR_PAGE;
            }
        }
        if (role != null) {
            switch (role) {
                case "lecturer":
                    session.setAttribute("updatecourseid", -1);
                    forward = Path.PAGE_LECTURER;
                    break;
                case "student":
                    forward = Path.PAGE_STUDENT;
                    if (user!=null) {
                        student = new DerbyStudentDAO().findStudentByIdUser(user.getIdUser());
                        coursesForUser = new DerbyCourseDAO().findAllCoursesThatUserNotRegistered(student.getId());
                    }
                    break;
                case "admin":
                    forward = Path.PAGE_ADMIN;
                    break;
            }
        }
        List<CourseDTO> courses = new DerbyCourseDAO().getAllCourses();
        List<ThemeDTO> themes = new DerbyThemeDAO().getAllThemes();
        List<LecturerDTO> lecturers = new DerbyLecturerDAO().getAllLecturers();
        session.setAttribute("themes", themes);
        session.setAttribute("lecturers", lecturers);
        session.setAttribute("courses", courses);
        request.setAttribute("courses", courses);
        session.setAttribute("coursesForUser", coursesForUser);
        if (student!=null) {
            session.setAttribute("idStudent", student.getId());
        }
        session.setAttribute("idTheme", null);
        session.setAttribute("idLecturer", null);
        session.setAttribute("sort", null);
        session.setAttribute("sorting", null);
        return forward;
    }
}