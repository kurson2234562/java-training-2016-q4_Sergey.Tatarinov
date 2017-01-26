package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.core.Utils;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLJournalDAO;
import ua.nure.tatarinov.SummaryTask4.exception.Errors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Update journal command
 * @author S. Tatarinov
 */
public class UpdateJournalCommand extends Command {

    /**
     * Logger for this command
     */
    public static final Logger LOG = Logger.getLogger(UpdateJournalCommand.class);

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 1924241214432006028L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing UpdateJournalCommand");
        HttpSession session = request.getSession();
        String test = request.getParameter("newValue");
        if (!Utils.isNumber(test)) {
            request.setAttribute("errorMessage", Errors.ERR_NOT_A_NUMBER);
            return Path.PAGE_ERROR_PAGE;
        } else {
            if (Integer.valueOf(test) > 100) {
                request.setAttribute("errorMessage", Errors.ERR_BELOW_THE_LIMIT);
                return Path.PAGE_ERROR_PAGE;
            } else if (Integer.valueOf(test) < 0) {
                request.setAttribute("errorMessage", Errors.ERR_ABOVE_THE_LIMIT);
                return Path.PAGE_ERROR_PAGE;
            }
            int id = Integer.parseInt(String.valueOf(session.getAttribute("id_student_course")));
            int newValue = Integer.parseInt(test);
            new MySQLJournalDAO().updateJournal(id, newValue);
            return Path.PAGE_LECTURER;
        }
    }
}