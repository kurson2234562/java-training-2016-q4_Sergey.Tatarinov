package ua.nure.tatarinov.SummaryTask4.db.dao.derby;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dao.StudentDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.StudentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DerbyStudentDAO implements StudentDAO {

    public static final Logger LOG = Logger.getLogger(DerbyStudentDAO.class);

    @Override
    public List<StudentDTO> findStudentsByString(String searchResult) {
        LOG.trace("Start tracing DerbyUserDAO#findStudentsByString");
        List<StudentDTO> students = new ArrayList<>();
        StudentDTO student;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.FIND_STUDENT_BY_STRING);
                statement.setString(1, searchResult + "%");
                statement.setString(2, searchResult + "%");
                statement.setString(3, searchResult + "%");
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    student = new StudentDTO(resultSet.getInt("id"), resultSet.getString("surname"),
                            resultSet.getString("name"), resultSet.getString("patronymic"),
                            resultSet.getInt("id_user"));
                    students.add(student);
                }
                resultSet.close();
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        return students;
    }

    @Override
    public StudentDTO findStudentByIdUser(int id) {
        LOG.trace("Start tracing DerbyStudentDAO#findStudentByIdUser");
        StudentDTO student = null;
        try (Connection connection = ConnectionPool.getConnetcion()){
            if (connection!=null){
                PreparedStatement statement = connection.prepareStatement(Query.SELECT_STUDENT_BY_ID_USER);
                connection.setAutoCommit(false);
                statement.setInt(1, id);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()){
                    student = new StudentDTO(resultSet.getInt("id"), resultSet.getString("surname"),
                            resultSet.getString("name"), resultSet.getString("patronymic"),
                            resultSet.getInt("id_user"));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage());
        }
        return student;
    }


    @Override
    public List<StudentDTO> getAllStudents() {
        LOG.trace("Starting tracing DerbyStudentDAO");
        List<StudentDTO> students = new ArrayList<>();
        StudentDTO student;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.SELECT_ALL_STUDENTS);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    student = new StudentDTO(resultSet.getInt("id"), resultSet.getString("surname"),
                            resultSet.getString("name"), resultSet.getString("patronymic"),
                            resultSet.getInt("id_user"));
                    students.add(student);
                }
                resultSet.close();

            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        return students;
    }
}
