package ua.nure.tatarinov.SummaryTask4.web.listener;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Context Listener
 * @author S. Tatarinov
 */
@WebListener()
public class ContextListener implements ServletContextListener{

    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    public ContextListener() {
    }

    public void contextInitialized(ServletContextEvent event) {
        LOG.trace("Servlet context initialization starts");

        ServletContext servletContext = event.getServletContext();
        initLog4J(servletContext);
        initCommandContainer();

        LOG.trace("Servlet context initialization finished");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        LOG.trace("Servlet context destruction starts");
        LOG.trace("Servlet context destruction finished");
    }

    private void initCommandContainer() {
        try {
            Class.forName("ua.nure.tatarinov.SummaryTask4.web.command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Cannot initialize Command Container");
        }
    }

    /**
     * Initialize log4j at startup web application
     * @param servletContext Context of servlet
     */
    private void initLog4J(ServletContext servletContext) {
        LOG.trace("Log4J initialization started");
        try {
            PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
            LOG.debug("Log4j has been initialized");
        } catch (Exception ex) {
            LOG.trace("Cannot configure Log4j");
            ex.printStackTrace();
        }
        LOG.trace("Log4J initialization finished");
    }

}
