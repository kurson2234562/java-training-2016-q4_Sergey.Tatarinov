package ua.nure.tatarinov.SummaryTask4.web.tag;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dto.JournalDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.StudentDTO;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class StudentJournalTag extends TagSupport {

    public static final Logger LOG = Logger.getLogger(StudentJournalTag.class);

    @Override
    public int doStartTag() throws JspException {
        int countInitials = 2;
        LOG.info("Start tracing StudentJournalTag");
        HttpSession session = pageContext.getSession();
        String idStudentCourse = String.valueOf(session.getAttribute("id_student_course"));
        session.setAttribute("id", idStudentCourse);
        String language = String.valueOf(session.getAttribute("language"));
        ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale(language));
        StudentDTO student = new StudentDTO();
        JournalDTO noteJournal = new JournalDTO();
        String patronymic = null;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.SELECT_STUDENT_FROM_JOURNAL);
                statement.setString(1, idStudentCourse);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    student.setName(resultSet.getString("name"));
                    student.setSurname(resultSet.getString("surname"));
                    patronymic = resultSet.getString("patronymic");
                    student.setPatronymic(patronymic);
                    if ((patronymic != null) && !(patronymic.isEmpty())) {
                        countInitials = 3;
                    }
                    noteJournal.setMark(Integer.parseInt(resultSet.getString("mark")));
                    noteJournal.setIdStudentCourse(Integer.parseInt(idStudentCourse));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage());
        }
        JspWriter out = pageContext.getOut();
        StringBuffer table = new StringBuffer();
        table.append("<table><tr>")
                .append("<th colspan=\"").append(countInitials).append("\">")
                .append(rb.getString("page.lecturer.student")).append("</th><th>")
                .append(rb.getString("page.student.mark")).append("</th></tr><tr><td>")
                .append(student.getSurname()).append("</td><td>")
                .append(student.getName()).append("</td><td>");
        if ((patronymic != null) && !(patronymic.isEmpty())) {
            table.append(student.getPatronymic()).append("</td><td>");
        }
        table.append(noteJournal.getMark()).append("</td></tr></table>");
        try {
            out.println(table);
        } catch (IOException e) {
            LOG.error(e.getLocalizedMessage());
        }

        return EVAL_PAGE;

    }
}