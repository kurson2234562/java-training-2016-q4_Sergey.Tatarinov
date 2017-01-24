package ua.nure.tatarinov.SummaryTask4.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dao.StudentDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.JournalDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.StudentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLStudentDAO implements StudentDAO {

    public static final Logger LOG = Logger.getLogger(MySQLStudentDAO.class);


    @Override
    public List<StudentDTO> findStudentsByString(String searchResult) {
        LOG.trace("Start tracing MySQLUserDAO#findStudentsByString");
        List<StudentDTO> students = new ArrayList<>();
        StudentDTO student;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.FIND_STUDENT_BY_STRING)) {
                    connection.setAutoCommit(false);
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
                    connection.commit();
                    resultSet.close();
                } catch (SQLException ex) {
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
        System.out.println(students);
        return students;
    }

    @Override
    public StudentDTO findStudentByIdUser(int id) {
        LOG.trace("Start tracing MySQLStudentDAO#findStudentByIdUser");
        StudentDTO student = null;
        try (Connection connection = ConnectionPool.getConnetcion()){
            if (connection!=null){
                try(PreparedStatement statement = connection.prepareStatement(Query.SELECT_STUDENT_BY_ID_USER)) {
                    connection.setAutoCommit(false);
                    statement.setInt(1, id);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        student = new StudentDTO(resultSet.getInt("id"), resultSet.getString("surname"),
                                resultSet.getString("name"), resultSet.getString("patronymic"),
                                resultSet.getInt("id_user"));
                    }
                    connection.commit();
                }catch (SQLException ex){
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage());
        }
        return student;
    }

    @Override
    public List<Integer> getStudentMarksById(int id) {
        LOG.trace("Start tracing MySQLStudentDAO#getStudentMarksById");
        List<Integer> marks = new ArrayList<>();
        StudentDTO student;
        JournalDTO noteJournal = new JournalDTO();
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_STUDENT_MARK_BY_ID)) {
                    connection.setAutoCommit(false);
                    statement.setInt(1, id);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()){
                        noteJournal.setIdStudentCourse(resultSet.getInt("id_student_course"));
                        noteJournal.setMark(resultSet.getInt("mark"));
                        marks.add(resultSet.getInt("mark"));
                    }
                    connection.commit();
                } catch (SQLException ex) {
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
        return marks;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        LOG.trace("Starting tracing MySQLStudentDAO");
        List<StudentDTO> students = new ArrayList<>();
        StudentDTO student;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                try(PreparedStatement statement = connection.prepareStatement(Query.SELECT_ALL_STUDENTS)) {
                    connection.setAutoCommit(false);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        student = new StudentDTO(resultSet.getInt("id"), resultSet.getString("surname"),
                                resultSet.getString("name"), resultSet.getString("patronymic"),
                                resultSet.getInt("id_user"));
                        students.add(student);
                    }
                    resultSet.close();
                    connection.commit();
                }catch (SQLException ex){
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
        return students;
    }
}