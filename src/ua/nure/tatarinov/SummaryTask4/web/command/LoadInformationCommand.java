package ua.nure.tatarinov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.Path;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dto.StudentDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Load Information command
 * @author S. Tatarinov
 */
public class LoadInformationCommand extends Command{

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 8017200174191193255L;

    /**
     * Logger for this command
     */
    public static final Logger LOG = Logger.getLogger(LoadInformationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Start tracing LoadInformationCommand");
        HttpSession session = request.getSession();
        String login = String.valueOf(session.getAttribute("username"));
        String role = String.valueOf(session.getAttribute("role"));
        String query = null;
        switch (role){
            case "student":
                query = Query.SELECT_USER_AS_STUDENT;
                break;
            case "lecturer":
                query = Query.SELECT_USER_AS_LECTURER;
                break;
            case "admin":
                query = Query.SELECT_USER_AS_ADMIN;
                break;
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
        session.setAttribute("info", student);
        session.setAttribute("userinfo", user);


        return Path.PAGE_EDIT_INFORMATION;
    }
}
