package ua.nure.tatarinov.SummaryTask4.controller;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.web.command.Command;
import ua.nure.tatarinov.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Controller.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.trace("*****************POST*****************");
        process(request, response, "post");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.trace("*****************GET*****************");
        process(request, response, "get");
    }

    private void process(HttpServletRequest request,
                         HttpServletResponse response, String method) throws IOException, ServletException {

        LOG.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: command --> " + commandName);

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);

        LOG.trace("Obtained command --> " + command);

        // execute command and get forward address
        String forward = Path.PAGE_ERROR_PAGE;
        try {
            forward = command.execute(request, response);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }
        LOG.trace("Forward address --> " + forward);

        LOG.debug("Controller finished, now go to forward address --> " + forward);

        // go to forward
        //response.sendRedirect(request.getContextPath()+"?command="+commandName);
        request.getRequestDispatcher(forward).forward(request, response);
    }
}