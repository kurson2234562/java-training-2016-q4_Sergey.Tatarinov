package ua.nure.tatarinov.SummaryTask4.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.AdminDAO;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Data access object for Admin.
 *
 * @author S. Tatarinov
 *
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
}
