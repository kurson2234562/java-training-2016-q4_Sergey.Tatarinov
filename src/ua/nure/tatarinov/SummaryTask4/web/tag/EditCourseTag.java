package ua.nure.tatarinov.SummaryTask4.web.tag;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyCourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyLecturerDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyStatusDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.derby.DerbyThemeDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.LecturerDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.StatusDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.ThemeDTO;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class EditCourseTag extends TagSupport {

    public static final Logger LOG = Logger.getLogger(EditCourseTag.class);

    private String method;

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public int doStartTag() throws JspException {
        LOG.info("Start tracing EditCourseTag");
        HttpSession session = pageContext.getSession();
        ResourceBundle rb = ResourceBundle.getBundle("resources",
                new Locale(String.valueOf(session.getAttribute("language"))));

        int id = -1;
        boolean methodIsUpdate = true;
        if (session.getAttribute("updatecourseid") != null) {
            id = Integer.parseInt(String.valueOf(session.getAttribute("updatecourseid")));
        } else {
            methodIsUpdate = false;
        }

        ThemeDTO theme = null;
        StatusDTO status = null;
        LecturerDTO lecturer = null;
        CourseDTO course = null;

        List<LecturerDTO> lecturers = new DerbyLecturerDAO().getAllLecturers();
        List<ThemeDTO> themes = new DerbyThemeDAO().getAllThemes();
        List<StatusDTO> statuses = new DerbyStatusDAO().getAllStatuses();
        course = new DerbyCourseDAO().getCourseByIdCourse(id);

        JspWriter out = pageContext.getOut();
        Iterator lecturerIt = lecturers.iterator();
        Iterator themeIt = themes.iterator();
        Iterator statusesIt = statuses.iterator();
        StringBuffer selects = new StringBuffer();


        /*-------------------Add fields for input name of course and duration--------------------------*/
        if (method.equals("create")) {
            selects.append("<td><input type=\"text\" name=\"name\" required></td>" +
                    "<td><input type=\"number\" name=\"duration\" min=\"1\" required></td>");
        } else if (method.equals("update")) {
            selects.append("<td><input type=\"text\" name=\"name\" required value=\"")
                    .append(course.getNameCourse())
                    .append("\"></td><td><input type=\"number\" name=\"duration\" min=\"1\" required value=\"")
                    .append(course.getDuration())
                    .append("\"></td>");
        }
        /*-----------------------------------------------------------------------------------------*/

        /*--------------------------------Add select of lecturers--------------------------------*/
        selects.append("<td><select name=\"lecturer\">");
        while (lecturerIt.hasNext()) {
            lecturer = (LecturerDTO) lecturerIt.next();
            selects.append("<option value=\"").append(lecturer.getId()).append("\"");
            if (methodIsUpdate) {
                if (lecturer.getId() == course.getIdLecturer()) {
                    selects.append(" selected ");
                }
            }
            selects.append(">")
                    .append(lecturer.getSurname()).append(" ")
                    .append(lecturer.getName()).append(" ")
                    .append(lecturer.getPatronymic()).append(" </option>");
        }
        selects.append("</select></td>");
        /*-----------------------------------------------------------------------------------------*/

        /*----------------------------------Add select of themes----------------------------------*/
        selects.append("<td><select name=\"theme\">");
        while (themeIt.hasNext()) {
            theme = (ThemeDTO) themeIt.next();
            selects.append("<option value=\"").append(theme.getIdTheme()).append("\"");
            if (methodIsUpdate) {
                if (theme.getIdTheme() == course.getIdTheme()) {
                    selects.append(" selected ");
                }
            }
            selects.append(">").append(theme.getNameTheme()).append("</option>");
        }
        selects.append("</select></td>");
        /*---------------------------------------------------------------------------------------*/

        /*--------------------------------Add select of statuses--------------------------------*/
        selects.append("<td><select name=\"status\">");
        while (statusesIt.hasNext()) {
            status = (StatusDTO) statusesIt.next();
            selects.append("<option value=\"").append(status.getIdStatus()).append("\"");
            if (methodIsUpdate) {
                if (status.getIdStatus() == course.getIdStatus()) {
                    selects.append(" selected ");
                }
            }
            selects.append(">").append(status.getNameStatus()).append("</option>");
        }
        selects.append("</select></td>");
        /*----------------------------------------------------------------------------------------*/

        try {
            out.println(selects);
        } catch (IOException e) {
            LOG.error(e.getLocalizedMessage());
        }
        return EVAL_PAGE;
    }
}