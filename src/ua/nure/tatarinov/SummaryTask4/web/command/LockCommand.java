package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyStudentOnCourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyUserDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.StudentOnCourseDTO;
import ua.nure.tatarinov.SummaryTask4.exception.Messages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static ua.nure.tatarinov.SummaryTask4.core.Utils.isDigit;

public class LockCommand extends Command {

    private static final Logger LOG = Logger.getLogger(LockCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing LockCommand");
        HttpSession session = request.getSession();
        String test = request.getParameter("id");
        String lock = request.getParameter("lock");
        int newValue = -1;
        boolean existStudent = false;
        if (!isDigit(test)) {
            request.setAttribute("errorMessage", Messages.ERR_NOT_A_NUMBER);
            return Path.PAGE_ERROR_PAGE;
        } else {
            List<StudentOnCourseDTO> students = new DerbyStudentOnCourseDAO().getAllStudentsOnCourse();
            for (StudentOnCourseDTO student : students){
                if (student.getIdStudentCourse() == Integer.parseInt(test)){
                    existStudent = true;
                    break;
                }
            }
            if (!existStudent){
                request.setAttribute("errorMessage", Messages.ERR_CANNOT_FIND_STUDENT);
                return Path.PAGE_ERROR_PAGE;
            }
        }
        switch (lock) {
            case "true":
                newValue = 0;
                break;
            case "false":
                newValue = 1;
                break;
            default:
                request.setAttribute("errorMessage", Messages.ERR_INVALID_VALUE_LOCK);
                return Path.PAGE_ERROR_PAGE;
        }
        new DerbyUserDAO().lockUserById(Integer.parseInt(test), newValue);
        return Path.PAGE_ADMIN;
    }
}