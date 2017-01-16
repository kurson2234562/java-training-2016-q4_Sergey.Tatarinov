package ua.nure.tatarinov.SummaryTask4.db.dao.derby;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dao.JournalDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DerbyJournalDAO implements JournalDAO {

    public static final Logger LOG = Logger.getLogger(DerbyJournalDAO.class);

    @Override
    public void updateJournal(int id, int mark) {
        LOG.trace("Starting trace DerbyJournalDAO");
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.UPDATE_JOURNAL, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, String.valueOf(mark));
                statement.setString(2, String.valueOf(id));
                statement.executeUpdate();
                connection.setAutoCommit(false);
            }
        }catch (SQLException e){
            LOG.error(e.getLocalizedMessage());
        }

    }
}
