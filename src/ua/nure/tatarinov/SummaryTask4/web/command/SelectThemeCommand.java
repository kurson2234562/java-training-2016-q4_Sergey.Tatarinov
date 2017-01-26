package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Select Theme command
 * @author S. Tatarinov
 */
@WebServlet(name = "SelectThemeCommand")
public class SelectThemeCommand extends Command {

    /**
     * Logger for this command
     */
    public static final Logger LOG = Logger.getLogger(SelectThemeCommand.class);

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -6211325981720125004L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Starting trace SelectThemeCommand");
        return Path.PAGE_COURSES;
    }
}

