package ua.nure.tatarinov.SummaryTask4.web.tag;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.LecturerDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.ThemeDTO;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserRegisteredCoursesTag extends TagSupport {

    public static final Logger LOG = Logger.getLogger(UserRegisteredCoursesTag.class);

    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int doStartTag() throws JspException {
        boolean hasLine = false;
        LOG.info("Start tracing UserRegisteredCoursesTag");
        HttpSession session = pageContext.getSession();
        String login = String.valueOf(session.getAttribute("username"));
        String language = String.valueOf(session.getAttribute("language"));
        List<LecturerDTO> lecturers = new ArrayList<>();
        List<ThemeDTO> themes = new ArrayList<>();
        List<CourseDTO> courses = new ArrayList<>();
        LecturerDTO lecturer;
        CourseDTO course;
        ThemeDTO theme;
        ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale(language));
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.SELECT_INFO_ABOUT_COURSE_BY_LOGIN_AND_BY_COURSE_STATUS);
                connection.setAutoCommit(false);
                statement.setString(1, login);
                statement.setString(2, status);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    lecturer = new LecturerDTO();
                    course = new CourseDTO();
                    theme = new ThemeDTO();
                    lecturer.setName(resultSet.getString("name"));
                    lecturer.setSurname(resultSet.getString("surname"));
                    lecturer.setPatronymic(resultSet.getString("patronymic"));
                    lecturers.add(lecturer);
                    course.setDuration(resultSet.getInt("duration"));
                    course.setNameCourse(resultSet.getString("name_course"));
                    courses.add(course);
                    theme.setNameTheme(resultSet.getString("name_theme"));
                    themes.add(theme);
                    hasLine = true;
                }
                resultSet.close();
            }
            LOG.trace("Data from DB successful obtained");
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        JspWriter out = pageContext.getOut();
        Iterator lecturersIt = lecturers.iterator();
        Iterator coursesIt = courses.iterator();
        Iterator themesIt = themes.iterator();
        StringBuffer table = new StringBuffer();
        if (hasLine) {
            table.append("<table class=\"table table-bordered table-striped\">").append("<th class=\"info\">")
                    .append(rb.getString("page.people.course.name")).append("</th><th class=\"info\">")
                    .append(rb.getString("page.people.course.duration")).append("</th><th class=\"info\">")
                    .append(rb.getString("page.student.theme")).append("</th><th class=\"info\" colspan=\"3\">")
                    .append(rb.getString("page.student.lecturer")).append("</th>");
            while (lecturersIt.hasNext()) {
                lecturer = (LecturerDTO) lecturersIt.next();
                course = (CourseDTO) coursesIt.next();
                theme = (ThemeDTO) themesIt.next();
                table.append("<tr><td>").append(course.getNameCourse()).append("</td><td>").append(course.getDuration())
                        .append("</td><td>").append(theme.getNameTheme()).append("</td><td>").append(lecturer.getSurname())
                        .append("</td><td>").append(lecturer.getName()).append("</td><td>").append(lecturer.getPatronymic())
                        .append("</td></tr>");
            }
        } else {
            table.append("<h3>").append(rb.getString("page.student.error.typecourse")).append("</h3>");
        }
        table.append("</table>");
        try {
            out.println(table);
        } catch (IOException e) {
            LOG.error(e.getLocalizedMessage());
        }

        return EVAL_PAGE;
    }
}