package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.exception.Errors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Language command
 * @author S. Tatarinov
 */
@WebServlet(name = "ChangeLanguage")
public class LanguageCommand extends Command {

    /**
     * Logger for this command
     */
    private static final Logger LOG = Logger.getLogger(LanguageCommand.class);

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 5063715519941606153L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing LanguageCommand");
        HttpSession session = request.getSession();
        List<String> languages = Arrays.asList("en", "ru", "uk");
        String language = request.getParameter("language");
        boolean existLanguage = false;
        for(String lang: languages){
            if (language.equals(lang)){
                existLanguage = true;
            }
        }
        if (!existLanguage){
            request.setAttribute("errorMessage", Errors.ERR_INVALID_VALUE_LANGUAGE);
            return Path.PAGE_ERROR_PAGE;
        }else {
            session.setAttribute("language", language);
        }
        return request.getContextPath();
    }
}
