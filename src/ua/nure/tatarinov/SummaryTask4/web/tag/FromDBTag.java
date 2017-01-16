package ua.nure.tatarinov.SummaryTask4.web.tag;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyThemeDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.ThemeDTO;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class FromDBTag extends TagSupport {

    public static final Logger LOG = Logger.getLogger(FromDBTag.class);

    @Override
    public int doStartTag() throws JspException {
        List<ThemeDTO> themes = new DerbyThemeDAO().getAllThemes();
        HttpSession session = pageContext.getSession();
        JspWriter out = pageContext.getOut();
        StringBuffer page = new StringBuffer();
        Iterator it = themes.iterator();
        while (it.hasNext()){
            String value = String.valueOf(it.next());
            page.append("<option name=\"themes\" value=\"").append(value).append("\">").append(value).append("</option>\n");
        }
        try {
            out.println("<select name=\"themes\">" + page + "</select>");
        } catch (IOException e) {
            LOG.info(e.getLocalizedMessage());
        }
        return 0;
    }
}
