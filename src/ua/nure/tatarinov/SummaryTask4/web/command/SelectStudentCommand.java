package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.mysql.MySQLStudentOnCourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.StudentOnCourseDTO;
import ua.nure.tatarinov.SummaryTask4.exception.Errors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static ua.nure.tatarinov.SummaryTask4.Path.PAGE_EDIT_JOURNAL;
import static ua.nure.tatarinov.SummaryTask4.core.Utils.isNumber;

/**
 * Select student command
 * @author S. Tatarinov
 */
public class SelectStudentCommand extends Command {

    /**
     * Logger for this command
     */
    public static final Logger LOG = Logger.getLogger(SelectStudentCommand.class);

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 6854843494018025061L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing SelectStudentCommand");
        String test = new String(request.getParameter("id").getBytes("ISO-8859-1"), "UTF-8");
        HttpSession session = request.getSession();
        List<StudentOnCourseDTO> students = new MySQLStudentOnCourseDAO().getAllStudentsOnCourse();
        boolean existStudent = false;
        if (!isNumber(test)) {
            request.setAttribute("errorMessage", Errors.ERR_NOT_A_NUMBER);
            return Path.PAGE_ERROR_PAGE;
        } else {
            for (StudentOnCourseDTO student : students) {
                if (student.getIdStudentCourse() == Integer.parseInt(test)) {
                    existStudent = true;
                }
            }
            if (!existStudent) {
                request.setAttribute("errorMessage", Errors.ERR_CANNOT_FIND_STUDENT);
                return Path.PAGE_ERROR_PAGE;
            } else {
                session.setAttribute("id_student_course", test);
                if (request.getParameter("mark") != null) {
                    request.setAttribute("mark", request.getParameter("mark"));
                }
                return PAGE_EDIT_JOURNAL;
            }
        }
    }
}