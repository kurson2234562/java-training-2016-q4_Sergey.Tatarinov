package ua.nure.tatarinov.SummaryTask4.web.tag;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;
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
import java.util.*;

public class JournalTag extends TagSupport {

    public static final Logger LOG = Logger.getLogger(JournalTag.class);

    @Override
    public int doStartTag() throws JspException {
        boolean hasLine = false;
        int countInitials = 2;
        LOG.info("Start tracing JournalTag");
        HttpSession session = pageContext.getSession();
        String login = String.valueOf(session.getAttribute("username"));
        String language = String.valueOf(session.getAttribute("language"));
        ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale(language));
        List<StudentDTO> students = new ArrayList<>();
        List<CourseDTO> courses = new ArrayList<>();
        List<JournalDTO> journal = new ArrayList<>();
        StudentDTO student = null;
        CourseDTO course = null;
        JournalDTO noteJournal = null;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.SELECT_INFO_ABOUT_STUDENTS_FROM_LECTURER);
                connection.setAutoCommit(false);
                statement.setString(1, login);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    student = new StudentDTO();
                    course = new CourseDTO();
                    noteJournal = new JournalDTO();
                    noteJournal.setIdStudentCourse(resultSet.getInt("id_student_course"));
                    student.setName(resultSet.getString("name"));
                    student.setSurname(resultSet.getString("surname"));
                    String patronymic = resultSet.getString("patronymic");
                    if (patronymic !=null){
                        countInitials = 3;
                    }
                    student.setPatronymic(patronymic);
                    students.add(student);
                    course.setNameCourse(resultSet.getString("name_course"));
                    course.setDuration(Integer.parseInt(resultSet.getString("duration")));
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
        Iterator studentIt = students.iterator();
        Iterator coursesIt = courses.iterator();
        Iterator journalIt = journal.iterator();
        StringBuffer table = new StringBuffer();
        if (hasLine) {
            table.append("<table>\n").append("<th>").append(rb.getString("page.people.course.name"))
                    .append("</th><th>").append(rb.getString("page.people.course.duration"))
                    .append("</th><th colspan=\"").append(countInitials).append("\">")
                    .append(rb.getString("page.lecturer.student"))
                    .append("</th><th>").append(rb.getString("page.student.mark"))
                    .append("</th><th>").append(rb.getString("page.people.course.actions"))
                    .append("</th>\n");
            while (studentIt.hasNext()) {
                student = (StudentDTO) studentIt.next();
                course = (CourseDTO) coursesIt.next();
                noteJournal = (JournalDTO) journalIt.next();
                table.append("\n<tr><td>").append(course.getNameCourse()).append("</td><td>")
                        .append(course.getDuration()).append("</td><td>")
                        .append(student.getSurname()).append("</td><td>")
                        .append(student.getName()).append("</td><td>");
                if ((student.getPatronymic()!=null) && hasLine) {
                    table.append(student.getPatronymic()).append("</td><td>");
                }else if (!hasLine){
                    table.append(student.getPatronymic()).append("</td><td>");
                }else{
                    table.append(student.getPatronymic()).append("</td><td>");
                }
                table.append(noteJournal.getMark())
                        .append("</td><td><form action=\"controller\">")
                        .append("<input type=\"hidden\" name=\"command\" value=\"selectStudentCommand\">")
                        .append("<input type=\"submit\" value=\"")
                        .append(rb.getString("page.lecturer.edit.mark"))
                        .append("\" id=\"edit\"/>\n")
                        .append("<input type=\"hidden\" name=\"id\" value=\"")
                        .append(noteJournal.getIdStudentCourse()).append("\"></form></td></tr>\n");
            }
            table.append("</table>");
        } else {
            table.append("<h3>").append(rb.getString("page.lecturer.error.mark")).append("</h3>");
        }
        try {
            out.println(table);
        } catch (IOException e) {
            LOG.error(e.getLocalizedMessage());
        }
        return EVAL_PAGE;
    }

}
