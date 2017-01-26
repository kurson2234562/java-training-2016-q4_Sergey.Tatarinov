package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Forget command
 * @author S. Tatarinov
 */
public class ForgetCommand extends Command{

    /**
     * Logger for this command
     */
    public static final Logger LOG = Logger.getLogger(ForgetCommand.class);

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 5797482214822872805L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing ForgetCommand");
        return Path.PAGE_FORGET;
    }
}
