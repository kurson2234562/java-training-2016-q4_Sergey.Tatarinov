package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyStudentOnCourseDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class InsertInJournalCommand extends Command {

    public static final Logger LOG = Logger.getLogger(InsertInJournalCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Starting trace InsertJournalCommand");
        HttpSession session = request.getSession();
        int mark = Integer.parseInt(request.getParameter("newValue"));
        int id = Integer.parseInt(String.valueOf(session.getAttribute("id_student_course")));
        new DerbyStudentOnCourseDAO().createMarkForStudent(mark, id);


        return Path.PAGE_LECTURER;
    }
}
