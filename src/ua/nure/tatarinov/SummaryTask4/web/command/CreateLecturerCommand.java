package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyLecturerDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyUserDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateLecturerCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CreateLecturerCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        int id = -1;
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        int idCourse = Integer.parseInt(request.getParameter("idCourse"));
        UserDTO user = new DerbyUserDAO().createUser(login, password);
        id = new DerbyLecturerDAO().createLecturer(surname, name, patronymic, user.getIdUser());
        if (id!=-1) {
            new DerbyLecturerDAO().changeLecturer(id, idCourse);
        }
        return Path.PAGE_COURSES;
    }
}