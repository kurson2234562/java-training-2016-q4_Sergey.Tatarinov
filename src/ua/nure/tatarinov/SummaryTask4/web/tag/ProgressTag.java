package ua.nure.tatarinov.SummaryTask4.web.tag;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.JournalDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.LecturerDTO;

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

public class ProgressTag extends TagSupport {

    public static final Logger LOG = Logger.getLogger(ProgressTag.class);

    @Override
    public int doStartTag() throws JspException {
        boolean hasLine = false;
        LOG.info("Start tracing ProgressTag");
        HttpSession session = pageContext.getSession();
        String login = String.valueOf(session.getAttribute("username"));
        String language = String.valueOf(session.getAttribute("language"));
        ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale(language));
        List<LecturerDTO> lecturers = new ArrayList<>();
        List<CourseDTO> courses = new ArrayList<>();
        List<JournalDTO> journal = new ArrayList<>();
        LecturerDTO lecturer;
        CourseDTO course;
        JournalDTO noteJournal;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.SELECT_PROGRESS_BY_USER);
                connection.setAutoCommit(false);
                statement.setString(1, login);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    lecturer = new LecturerDTO();
                    course = new CourseDTO();
                    noteJournal = new JournalDTO();
                    lecturer.setName(resultSet.getString("name"));
                    lecturer.setSurname(resultSet.getString("surname"));
                    lecturer.setPatronymic(resultSet.getString("patronymic"));
                    lecturers.add(lecturer);
                    course.setNameCourse(resultSet.getString("name_course"));
                    courses.add(course);
                    noteJournal.setMark(Integer.parseInt(resultSet.getString("mark")));
                    journal.add(noteJournal);
                    hasLine = true;
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage());
        }
        JspWriter out = pageContext.getOut();
        Iterator lecturersIt = lecturers.iterator();
        Iterator coursesIt = courses.iterator();
        Iterator journalIt = journal.iterator();
        StringBuffer table = new StringBuffer();
        if (hasLine) {
            table.append("<table>").append("<th>").append(rb.getString("page.people.course.name"))
                    .append("</th><th colspan=\"3\">").append(rb.getString("page.student.lecturer"))
                    .append("</th><th>").append(rb.getString("page.student.mark")).append("</th>");
            while (lecturersIt.hasNext()) {
                lecturer = (LecturerDTO) lecturersIt.next();
                course = (CourseDTO) coursesIt.next();
                noteJournal = (JournalDTO) journalIt.next();
                table.append("<tr><td>").append(course.getNameCourse()).append("</td><td>")
                        .append(lecturer.getSurname()).append("</td><td>")
                        .append(lecturer.getName()).append("</td><td>")
                        .append(lecturer.getPatronymic()).append("</td><td>")
                        .append(noteJournal.getMark()).append("</td></tr>");
            }
            table.append("</table>");
        } else {
            table.append("<h3>").append(rb.getString("page.student.error.mark")).append("</h3>");
        }
        try {
            out.println(table);
        } catch (IOException e) {
            LOG.error(e.getLocalizedMessage());
        }

        return EVAL_PAGE;
    }

}