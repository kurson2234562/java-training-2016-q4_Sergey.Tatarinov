package ua.nure.tatarinov.SummaryTask4.web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Main interface for the Command pattern implementation
 */
public abstract class Command implements Serializable {
    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 2130509003029456001L;

    /**
     * Execution method for command.
     * @return Address to go once the command is executed.
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    /**
     * Gets command name as request string.
     * @return request string.
     */
    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }

}
