package ua.nure.tatarinov.SummaryTask4.db.dao.mysql;

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

public class MySQLLecturerDAO implements LecturerDAO {

    public static final Logger LOG = Logger.getLogger(MySQLLecturerDAO.class);

    @Override
    public List<LecturerDTO> getAllLecturers() {
        LOG.trace("Starting tracing MySQLLecturerDAO#getAllLecturers");
        List<LecturerDTO> lecturers = new ArrayList<>();
        LecturerDTO lecturer;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_ALL_LECTURERS)) {
                    connection.setAutoCommit(false);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        lecturer = new LecturerDTO(resultSet.getInt("id"), resultSet.getString("surname"),
                                resultSet.getString("name"), resultSet.getString("patronymic"), resultSet.getInt("id_user"));
                        lecturers.add(lecturer);
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
        return lecturers;
    }

    @Override
    public int createLecturer(String surname, String name, String patronymic, int idUser) {
        LOG.trace("Starting tracing MySQLLecturerDAO#createLecturer");
        int id = -1;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.CREATE_LECTURER)) {
                    connection.setAutoCommit(false);
                    statement.setString(1, surname);
                    statement.setString(2, name);
                    statement.setString(3, patronymic);
                    statement.setInt(4, idUser);
                    statement.execute();

                    PreparedStatement stmt = connection.prepareStatement(Query.SELECT_LAST_LECTURER_ID);
                    stmt.execute();
                    ResultSet resultSet = stmt.getResultSet();
                    if (resultSet.next()) {
                        id = resultSet.getInt("max(id)");
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
        return id;
    }

    @Override
    public void changeLecturer(int id, int idCourse) {
        LOG.trace("Starting tracing MySQLLecturerDAO#changeLecturer");
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                try(PreparedStatement statement = connection.prepareStatement(Query.CHANGE_LECTURER)) {
                    connection.setAutoCommit(false);
                    statement.setInt(1, id);
                    statement.setInt(2, idCourse);
                    statement.execute();
                    connection.commit();
                }catch (SQLException ex){
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage());
        }

    }

    @Override
    public List<LecturerDTO> findLecturersByString(String searchResult) {
        LOG.trace("Start tracing MySQLLecturerDAO#findLecturersByString");
        List<LecturerDTO> lecturers = new ArrayList<>();
        LecturerDTO lecturer;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.FIND_LECTURER_BY_STRING);
                statement.setString(1, searchResult + "%");
                statement.setString(2, searchResult + "%");
                statement.setString(3, searchResult + "%");
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    lecturer = new LecturerDTO(resultSet.getInt("id"), resultSet.getString("surname"),
                            resultSet.getString("name"), resultSet.getString("patronymic"),
                            resultSet.getInt("id_user"));
                    lecturers.add(lecturer);
                }
                resultSet.close();
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        return lecturers;
    }
}
