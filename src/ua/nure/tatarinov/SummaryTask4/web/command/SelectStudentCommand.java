package ua.nure.tatarinov.SummaryTask4.web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.nure.tatarinov.SummaryTask4.Path.PAGE_EDIT_JOURNAL;

public class SelectStudentCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id_student_course = request.getParameter("id");
        HttpSession session = request.getSession();
        session.setAttribute("id_student_course",id_student_course);
        if (request.getParameter("mark")!=null){
            request.setAttribute("mark", request.getParameter("mark"));
        }
        return PAGE_EDIT_JOURNAL;
    }

}
