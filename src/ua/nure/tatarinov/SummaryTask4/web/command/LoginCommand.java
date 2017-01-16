package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyCourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyLecturerDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyThemeDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyUserDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.LecturerDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.ThemeDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.UserDTO;

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
        String role = null;
        LOG.info("UserDTO " + login + " logged");
        if ((!login.isEmpty()) && (!password.isEmpty())) {
            UserDTO user = new DerbyUserDAO().findUserByLogin(login);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    if (user.getRoleId() == 0) {
                        role = "admin";
                    } else if (user.getRoleId() == 1) {
                        role = "student";
                    } else {
                        role = "lecturer";
                    }
                    request.setAttribute("username", login);
                    request.setAttribute("role", role);
                    request.setAttribute("password", password);
                    session.setAttribute("username", login);
                    session.setAttribute("role", role);
                    session.setAttribute("password", password);
                } else {
                    forward = "/";
                }
            }
            session.setAttribute("user", user);
        }
        if (role != null) {
            switch (role) {
                case "lecturer":
                    //request.getRequestDispatcher("/WEB-INF/jsp/lecturer.jsp").forward(request, response);
                    session.setAttribute("updatecourseid", -1);
                    forward = Path.PAGE_LECTURER;
                    break;
                case "student":
                    //request.getRequestDispatcher("/WEB-INF/jsp/student.jsp").forward(request, response);
                    forward = Path.PAGE_STUDENT;
                    break;
                case "admin":
                    //request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
                    forward = Path.PAGE_ADMIN;
                    break;
            }
        }
        List<CourseDTO> courses = new DerbyCourseDAO().getAllCourses();
        List<ThemeDTO> themes = new DerbyThemeDAO().getAllThemes();
        List<LecturerDTO> lecturers = new DerbyLecturerDAO().getAllLecturers();
        session.setAttribute("themes", themes);
        session.setAttribute("lecturers", lecturers);
        request.setAttribute("courses", courses);
        session.setAttribute("courses", courses);

        session.setAttribute("idTheme", null);
        session.setAttribute("idLecturer", null);
        session.setAttribute("sort", null);
        session.setAttribute("sorting", null);

        return forward;
    }
}