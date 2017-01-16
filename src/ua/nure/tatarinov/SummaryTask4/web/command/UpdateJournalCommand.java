package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyJournalDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.nure.tatarinov.SummaryTask4.core.Utils.isDigit;

public class UpdateJournalCommand extends Command {

    public static final Logger LOG = Logger.getLogger(UpdateJournalCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing UpdateJournalCommand");
        HttpSession session = request.getSession();
        String test = request.getParameter("newValue");
        if (!isDigit(test)) {
            request.setAttribute("errorMessage", "The input string is not a number");
            return Path.PAGE_ERROR_PAGE;
        } else {
            if (Integer.valueOf(test) < 0) {
                request.setAttribute("errorMessage", "The input string is not a number");
                return Path.PAGE_ERROR_PAGE;
            } else if (Integer.valueOf(test) > 100) {
                request.setAttribute("errorMessage", "The input string is not a number");
                return Path.PAGE_ERROR_PAGE;
            }

            int id = Integer.parseInt(String.valueOf(session.getAttribute("id_student_course")));
            int newValue = Integer.parseInt(test);
            new DerbyJournalDAO().updateJournal(id, newValue);
            return Path.PAGE_LECTURER;
        }
    }
}