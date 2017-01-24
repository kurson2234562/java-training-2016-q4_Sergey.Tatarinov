package ua.nure.tatarinov.SummaryTask4.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dao.ThemeDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.ThemeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLThemeDAO implements ThemeDAO {

    public static final Logger LOG = Logger.getLogger(MySQLThemeDAO.class);

    @Override
    public List<ThemeDTO> getAllThemes() {
        LOG.trace("Starting tracing MySQLThemeDAO");
        List<ThemeDTO> themes = new ArrayList<>();
        ThemeDTO theme;

        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_ALL_THEMES)) {
                    connection.setAutoCommit(false);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        theme = new ThemeDTO(resultSet.getInt("id_theme"), resultSet.getString("name_theme"));
                        themes.add(theme);
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

        return themes;
    }
}