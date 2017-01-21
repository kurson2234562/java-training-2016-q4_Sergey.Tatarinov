package ua.nure.tatarinov.SummaryTask4.web.tag;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dto.StudentDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.UserDTO;
import ua.nure.tatarinov.SummaryTask4.db.Query;

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


public class AboutTag extends TagSupport {

    public static final Logger LOG = Logger.getLogger(AboutTag.class);


    @Override
    public int doStartTag() throws JspException {
        String query;
        LOG.info("Start tracing AboutTag");
        HttpSession session = pageContext.getSession();
        String role = String.valueOf(session.getAttribute("role"));
        String login = String.valueOf(session.getAttribute("username"));
        String language = String.valueOf(session.getAttribute("language"));
        ResourceBundle rb = ResourceBundle.getBundle("resources", new Locale(language));
        StringBuffer localeRole = new StringBuffer();
        LOG.info("login is " + login + " role is " + role);
        switch (role) {
            case "admin":
                query = Query.SELECT_USER_AS_ADMIN;
                localeRole.append("<dt>").append(rb.getString("page.people.role"))
                        .append("</dt><dd>").append(rb.getString("page.people.role.admin")).append("</dd>");
                break;
            case "student":
                query = Query.SELECT_USER_AS_STUDENT;
                localeRole.append("<dt>").append(rb.getString("page.people.role"))
                        .append("</dt><dd>").append(rb.getString("page.people.role.student")).append("</dd>");
                break;
            case "lecturer":
                query = Query.SELECT_USER_AS_LECTURER;
                localeRole.append("<dt>").append(rb.getString("page.people.role"))
                        .append("</dt><dd>").append(rb.getString("page.people.role.lecturer")).append("</dd>");
                break;
            default:
                return -1;
        }
        StudentDTO student = null;
        UserDTO user = null;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(query);
                connection.setAutoCommit(false);
                statement.setString(1, login);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    user = new UserDTO(resultSet.getInt("id_user"), resultSet.getString("login"),
                            resultSet.getString("password"), resultSet.getString("email"),
                            resultSet.getInt("id_role"), resultSet.getInt("id_state"));
                    student = new StudentDTO();
                    student.setName(resultSet.getString("name"));
                    student.setSurname(resultSet.getString("surname"));
                    student.setPatronymic(resultSet.getString("patronymic"));
                }
                resultSet.close();
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        JspWriter out = pageContext.getOut();
        StringBuffer patronymic = new StringBuffer();
        StringBuffer page = new StringBuffer();
        StringBuffer email = new StringBuffer();
        if (user != null) {
            if ((user.getEmail()!=null) && (!user.getEmail().isEmpty())){
                email.append("<dt>Email</dt><dd>")
                        .append(user.getEmail()).append("</dd>");
            }
            if ((student.getPatronymic() != null) && !(student.getPatronymic().isEmpty())) {
                patronymic.append("<dt>").append(rb.getString("page.people.patronymic"))
                        .append("</dt><dd>").append(student.getPatronymic()).append("</dd>");
            }
            page.append("<dt>").append(rb.getString("page.people.surname"))
                    .append("</dt><dd>").append(student.getSurname())
                    .append("</dd><dt>").append(rb.getString("page.people.name"))
                    .append("</dt><dd>").append(student.getName()).append("</dd>")
                    .append(patronymic)
                    .append(email)
                    .append("<dt>").append(rb.getString("page.people.password")).append("</dt><dd>")
                    .append(user.getPassword()).append("</dd>")
                    .append("<dt>").append(rb.getString("page.people.login"))
                    .append("</dt><dd>").append(user.getLogin()).append("</dd>")
                    .append(localeRole);
        }
        try {
            out.println(page);
        } catch (IOException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        return EVAL_PAGE;
    }
}