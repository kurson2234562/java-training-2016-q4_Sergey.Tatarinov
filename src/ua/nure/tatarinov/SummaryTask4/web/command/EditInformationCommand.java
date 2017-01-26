package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLAdminDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLLecturerDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLStudentDAO;
import ua.nure.tatarinov.SummaryTask4.exception.Errors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Edit information command
 * @author S. Tatarinov
 */
public class EditInformationCommand extends Command {

    /**
     * Logger for this command
     */
    private static final Logger LOG = Logger.getLogger(EditInformationCommand.class);

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 9173324385175605800L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing EditInformationCommand");
        HttpSession session = request.getSession();
        int idUser = Integer.parseInt(String.valueOf(session.getAttribute("idUser")));
        String role = String.valueOf(session.getAttribute("role"));
        String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
        String surname = new String(request.getParameter("surname").getBytes("ISO-8859-1"), "UTF-8");
        String patronymic = new String(request.getParameter("patronymic").getBytes("ISO-8859-1"), "UTF-8");
        String login = new String(request.getParameter("login").getBytes("ISO-8859-1"), "UTF-8");
        String email = new String(request.getParameter("email").getBytes("ISO-8859-1"), "UTF-8");
        if ((!name.equals(null)) || (!name.isEmpty()) || (!login.equals(null)) || (!login.isEmpty()) || (!surname.equals(null)) || (!surname.isEmpty())) {
            switch (role) {
                case "student":
                    new MySQLStudentDAO().updateStudentById(idUser, surname, name, patronymic, login, email);
                    break;
                case "lecturer":
                    new MySQLLecturerDAO().updateLecturerById(idUser, surname, name, patronymic, login, email);
                    break;
                case "admin":
                    new MySQLAdminDAO().updateAdminById(idUser, surname, name, patronymic, login, email);
                    break;
            }
        }else {
            request.setAttribute("errorMessage", Errors.ERR_DONT_ENTER_SUFFICIENT_PARAMETERS);
        }
        return Path.PAGE_HOME;
    }
}