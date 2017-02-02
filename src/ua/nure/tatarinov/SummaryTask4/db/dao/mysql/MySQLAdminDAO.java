package ua.nure.tatarinov.SummaryTask4.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.AdminDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.JournalDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.StudentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object for Admin.
 * @author S. Tatarinov
 */

public class MySQLAdminDAO implements AdminDAO {

    public static final Logger LOG = Logger.getLogger(MySQLAdminDAO.class);

    @Override
    public void updateAdminById(int idUser, String surname, String name, String patronymic, String login, String email) {
        LOG.trace("Start tracing MySQLAdminDAO#updateAdminById");
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.UPDATE_ADMIN)) {
                    connection.setAutoCommit(false);
                    statement.setString(1, surname);
                    statement.setString(2, name);
                    statement.setString(3, patronymic);
                    statement.setInt(4, idUser);
                    statement.execute();

                    PreparedStatement stmt = connection.prepareStatement(Query.UPDATE_USER);
                    stmt.setString(1, login);
                    stmt.setString(2, email);
                    stmt.setInt(3, idUser);
                    stmt.execute();

                    connection.commit();
                } catch (SQLException ex) {
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
    }

    @Override
    public String selectTopMark() {
        StringBuilder page = new StringBuilder();
        JournalDTO journalDTO = null;
        CourseDTO courseDTO = null;
        LOG.trace("Start tracing MySQLAdminDAO#selectTopMark");
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.TOP_MARK)) {
                    connection.setAutoCommit(false);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        journalDTO = new JournalDTO();
                        courseDTO =new CourseDTO();
                        journalDTO.setMark(resultSet.getInt("max(mark)"));
                        courseDTO.setNameCourse(resultSet.getString("name_course"));
                        page.append("<tr><td>").append(courseDTO.getNameCourse())
                                .append("</td><td>").append(journalDTO.getMark())
                                .append("</td></tr>");
                    }

                    connection.commit();
                } catch (SQLException ex) {
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        return page.toString();
    }

    @Override
    public String selectAvgMark() {
        StudentDTO studentDTO = null;
        JournalDTO journalDTO = null;
        StringBuilder page = new StringBuilder();
        LOG.trace("Start tracing MySQLAdminDAO#selectAvgMark");
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.AVG_MARK)) {
                    connection.setAutoCommit(false);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()){
                        studentDTO = new StudentDTO();
                        journalDTO = new JournalDTO();
                        studentDTO.setName(resultSet.getString("name"));
                        studentDTO.setPatronymic(resultSet.getString("patronymic"));
                        studentDTO.setSurname(resultSet.getString("surname"));
                        journalDTO.setMark(resultSet.getInt("avg(mark)"));
                        page.append("<tr><td>").append(studentDTO.getSurname())
                                .append("</td><td>").append(studentDTO.getName()).append("</td><td>")
                                .append(studentDTO.getPatronymic()).append("</td><td>").append(journalDTO.getMark())
                                .append("</td></tr>");
                    }
                    resultSet.close();
                    connection.commit();
                } catch (SQLException ex) {
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        return page.toString();
    }
}
