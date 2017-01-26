package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.core.Utils;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLUserDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.UserDTO;
import ua.nure.tatarinov.SummaryTask4.exception.Errors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Forget password command
 * @author S. Tatarinov
 */
public class ForgetPasswordCommand extends Command {

    /**
     * Logger for this command
     */
    public static final Logger LOG = Logger.getLogger(ForgetPasswordCommand.class);

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -5453190283513368229L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing ForgetPasswordCommand");
        String email = new String(request.getParameter("email").getBytes("ISO-8859-1"), "UTF-8");
        List<UserDTO> users = new MySQLUserDAO().getAllUsers();
        for (UserDTO user : users) {
            if (user.getEmail().equals(email) || user.getLogin().equals(email)) {
                if ((user.getEmail() != null) || (!user.getEmail().isEmpty())) {
                    Utils.sendMail(user.getEmail(), Utils.generateMessage(user.getLogin()));
                    break;
                }else {
                    request.setAttribute("errorMessage", Errors.ERR_USER_HAS_NO_EMAIL);
                    return Path.PAGE_ERROR_PAGE;
                }
            } else {
                request.setAttribute("errorMessage", Errors.ERR_NOT_FIND_USER);
                return Path.PAGE_ERROR_PAGE;
            }
        }
        return Path.PAGE_LOGIN;
    }
}