package ua.nure.tatarinov.SummaryTask4.web.command;

import ua.nure.tatarinov.SummaryTask4.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutCommand")
public class LogoutCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("role", null);
        session.setAttribute("username", null);
        session.setAttribute("password", null);
        session.setAttribute("idTheme", null);
        session.setAttribute("idLecturer", null);
        session.setAttribute("sort", null);
        session.setAttribute("sorting", null);
        if (session!=null){
            session.invalidate();
        }
        return Path.PAGE_LOGIN;
    }
}

