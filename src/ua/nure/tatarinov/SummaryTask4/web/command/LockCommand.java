package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyUserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LockCommand extends Command {

    private static final Logger LOG = Logger.getLogger(LockCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing LockCommand");
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String lock = request.getParameter("lock");
        int newValue = -1;
        switch (lock) {
            case "true":
                newValue = 0;
                break;
            case "false":
                newValue = 1;
                break;
        }
        new DerbyUserDAO().lockUserById(id, newValue);
        return Path.PAGE_ADMIN;
    }
}