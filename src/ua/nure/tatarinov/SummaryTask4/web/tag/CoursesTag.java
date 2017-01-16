package ua.nure.tatarinov.SummaryTask4.web.tag;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dto.*;

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

public class CoursesTag extends TagSupport {

    public static final Logger LOG = Logger.getLogger(CoursesTag.class);

    @Override
    public int doStartTag() throws JspException {
        boolean hasLine = false;
        LOG.info("Start tracing CoursesTag");
        HttpSession session = pageContext.getSession();
        String language = String.valueOf(session.getAttribute("language"));
        ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale(language));
        List<LecturerDTO> lecturers = new ArrayList<>();
        List<CourseDTO> courses = new ArrayList<>();
        List<ThemeDTO> themes = new ArrayList<>();
        List<StatusDTO> statuses = new ArrayList<>();
        List<Integer> listOfCount = new ArrayList<>();
        LecturerDTO lecturer = null;
        CourseDTO course = null;
        ThemeDTO theme = null;
        StatusDTO status = null;
        int count = -1;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.SELECT_ALL_INFO_ABOUT_COURSES);
                connection.setAutoCommit(false);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    lecturer = new LecturerDTO();
                    course = new CourseDTO();
                    theme = new ThemeDTO();
                    status = new StatusDTO();
                    lecturer.setName(resultSet.getString("name"));
                    lecturer.setSurname(resultSet.getString("surname"));
                    lecturer.setPatronymic(resultSet.getString("patronymic"));
                    lecturers.add(lecturer);
                    course.setNameCourse(resultSet.getString("name_course"));
                    course.setDuration(Integer.parseInt(resultSet.getString("duration")));
                    courses.add(course);
                    theme.setNameTheme(resultSet.getString("name_theme"));
                    themes.add(theme);
                    status.setNameStatus(resultSet.getString("name_status"));
                    statuses.add(status);
                    listOfCount.add(Integer.parseInt(resultSet.getString("COUNT")));
                    hasLine = true;
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage());
        }
        JspWriter out = pageContext.getOut();
        Iterator lecturerIt = lecturers.iterator();
        Iterator coursesIt = courses.iterator();
        Iterator themeIt = themes.iterator();
        Iterator statusesIt = statuses.iterator();
        Iterator countListIt = listOfCount.iterator();
        StringBuffer table = new StringBuffer();

        if (hasLine) {
            table.append("<table>\n").append("<th>").append(rb.getString("page.people.course.name"))
                    .append("</th><th>").append(rb.getString("page.people.course.duration"))
                    .append("</th><th>").append(rb.getString("page.student.theme"))
                    .append("</th><th colspan=\"3\">").append(rb.getString("page.student.lecturer"))
                    .append("</th><th>").append(rb.getString("page.courses.table.status"))
                    .append("</th><th>").append(rb.getString("page.courses.table.count")).append("</th>\n");
            while (lecturerIt.hasNext()) {
                lecturer = (LecturerDTO) lecturerIt.next();
                course = (CourseDTO) coursesIt.next();
                theme = (ThemeDTO) themeIt.next();
                status = (StatusDTO) statusesIt.next();
                count = (int) countListIt.next();
                table.append("<tr>\n<td>")
                        .append(course.getNameCourse()).append("</td>\n<td>")
                        .append(course.getDuration()).append("</td>\n<td>")
                        .append(theme.getNameTheme()).append("</td>\n<td>")
                        .append(lecturer.getSurname()).append("</td>\n<td>")
                        .append(lecturer.getName()).append("</td>\n<td>")
                        .append(lecturer.getPatronymic()).append("</td>\n<td>")
                        .append(status.getNameStatus()).append("</td>\n<td>")
                        .append(count).append("</td>\n</tr>\n");
            }
            table.append("</table>");
        } else {
            table.append("<h3>").append(rb.getString("page.courses.error")).append("</h3>");
        }
        try {
            out.println(table);
        } catch (IOException e) {
            LOG.error(e.getLocalizedMessage());
        }
        return EVAL_PAGE;
    }
}