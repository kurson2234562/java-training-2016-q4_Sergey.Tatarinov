package ua.nure.tatarinov.SummaryTask4.web.tag;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyCourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SelectCourseTag extends TagSupport {
    public static final Logger LOG = Logger.getLogger(SelectCourseTag.class);

    @Override
    public int doStartTag() throws JspException {
        boolean hasLine = false;
        LOG.info("Start tracing CreateCourseTag");
        HttpSession session = pageContext.getSession();
        String language = String.valueOf(session.getAttribute("language"));
        ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale(language));
        List<CourseDTO> courses = new DerbyCourseDAO().getAllCourses();
        CourseDTO course = null;
        JspWriter out = pageContext.getOut();
        Iterator coursesIt = courses.iterator();
        StringBuffer select = new StringBuffer();

        select.append("<select name=\"id\" class=\"form-control select\">");
        while (coursesIt.hasNext()) {
            course = (CourseDTO) coursesIt.next();
            select.append("<option value=\"").append(course.getIdCourse()).append("\">")
                    .append(course.getNameCourse()).append(" </option>");
        }
        select.append("</select>");
        try {
            out.println(select);
        } catch (IOException e) {
            LOG.error(e.getLocalizedMessage());
        }

        return EVAL_PAGE;
    }
}