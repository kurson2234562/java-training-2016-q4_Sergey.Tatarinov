package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLStudentOnCourseDAO;
import ua.nure.tatarinov.SummaryTask4.exception.Errors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.nure.tatarinov.SummaryTask4.core.Utils.isNumber;

/**
 * Insert Journal command
 * @author S. Tatarinov
 */
public class InsertInJournalCommand extends Command {

    /**
     * Logger for this command
     */
    public static final Logger LOG = Logger.getLogger(InsertInJournalCommand.class);

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 9205997128370138418L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Starting trace InsertJournalCommand");
        HttpSession session = request.getSession();
        String test = new String(request.getParameter("newValue").getBytes("ISO-8859-1"), "UTF-8");
        if (!isNumber(test)) {
            request.setAttribute("errorMessage", Errors.ERR_NOT_A_NUMBER);
            return Path.PAGE_ERROR_PAGE;
        } else {
            if (Integer.valueOf(test) < 0) {
                request.setAttribute("errorMessage", Errors.ERR_ABOVE_THE_LIMIT);
                return Path.PAGE_ERROR_PAGE;
            } else if (Integer.valueOf(test) > 100) {
                request.setAttribute("errorMessage", Errors.ERR_BELOW_THE_LIMIT);
                return Path.PAGE_ERROR_PAGE;
            }
            int mark = Integer.parseInt(request.getParameter("newValue"));
            int id = Integer.parseInt(String.valueOf(session.getAttribute("id_student_course")));
            new MySQLStudentOnCourseDAO().createMarkForStudent(mark, id);
            return Path.PAGE_LECTURER;
        }
    }
}
