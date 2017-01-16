package ua.nure.tatarinov.SummaryTask4.db.dao.derby;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dao.StatusDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.StatusDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DerbyStatusDAO implements StatusDAO {

    public static final Logger LOG = Logger.getLogger(DerbyStatusDAO.class);

    @Override
    public List<StatusDTO> getAllStatuses() {
        LOG.trace("Starting tracing DerbyStatusDAO");
        List<StatusDTO> statuses = new ArrayList<>();
        StatusDTO status;

        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.SELECT_STATUSES);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    status = new StatusDTO(resultSet.getInt("id_status"), resultSet.getString("name_status"));
                    statuses.add(status);
                }
                resultSet.close();

            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        return statuses;
    }
}
