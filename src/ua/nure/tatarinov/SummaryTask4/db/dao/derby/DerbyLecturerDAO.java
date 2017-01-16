package ua.nure.tatarinov.SummaryTask4.db.dao.derby;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dao.LecturerDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.LecturerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DerbyLecturerDAO implements LecturerDAO {

    public static final Logger LOG = Logger.getLogger(DerbyLecturerDAO.class);

    @Override
    public List<LecturerDTO> getAllLecturers() {
        LOG.trace("Starting tracing DerbyLecturerDAO");
        List<LecturerDTO> lecturers = new ArrayList<>();
        LecturerDTO lecturer;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.SELECT_LECTURERS);
                connection.setAutoCommit(false);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    lecturer = new LecturerDTO(resultSet.getInt("id"), resultSet.getString("surname"),
                            resultSet.getString("name"), resultSet.getString("patronymic"),resultSet.getInt("id_user"));
                    lecturers.add(lecturer);
                }
                resultSet.close();

            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        return lecturers;
    }

    @Override
    public int createLecturer(String surname, String name, String patronymic, int idUser) {
        int id = -1;
        LecturerDTO lecturer = new LecturerDTO();
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.CREATE_LECTURER);
                statement.setString(1, surname);
                statement.setString(2, name);
                statement.setString(3, patronymic);
                statement.setInt(4, idUser);
                statement.execute();
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.SELECT_LAST_LECTURER_ID);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    id = resultSet.getInt("1");
                }
                resultSet.close();
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        return id;
    }

    @Override
    public void changeLecturer(int id, int idCourse) {
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.CHANGE_LECTURER);
                statement.setInt(1, id);
                statement.setInt(2,idCourse);
                statement.execute();
            }
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage());
        }

    }
}
