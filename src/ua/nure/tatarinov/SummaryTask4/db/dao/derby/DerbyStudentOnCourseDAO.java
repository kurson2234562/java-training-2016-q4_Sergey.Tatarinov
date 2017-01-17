package ua.nure.tatarinov.SummaryTask4.db.dao.derby;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dao.StudentOnCourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.StudentOnCourseDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DerbyStudentOnCourseDAO implements StudentOnCourseDAO {

    public static final Logger LOG = Logger.getLogger(DerbyStudentOnCourseDAO.class);


    @Override
    public List<StudentOnCourseDTO> getAllStudentsOnCourse() {
        LOG.trace("Starting tracing StudentOnCourseDAO#getAllStudentsOnCourse");
        List<StudentOnCourseDTO> students = new ArrayList<>();
        StudentOnCourseDTO studentOnCourse;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.SELECT_ALL_STUDENT_ON_COURSE);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    studentOnCourse = new StudentOnCourseDTO(resultSet.getInt("id_student_course"),
                            resultSet.getInt("id_student"), resultSet.getInt("id_course"));
                    students.add(studentOnCourse);
                }
                resultSet.close();
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        return students;
    }

    @Override
    public List<StudentOnCourseDTO> getCountStudentPerCourse() {
        LOG.trace("Starting tracing StudentOnCourseDAO#getCountStudentPerCourse");
        List<StudentOnCourseDTO> counts = new ArrayList<>();
        StudentOnCourseDTO count;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.SELECT_COUNT_STUDENTS_PER_COURSE);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    count = new StudentOnCourseDTO(resultSet.getInt("id_student_course"),
                            resultSet.getInt("id_student"), resultSet.getInt("id_course"));
                    counts.add(count);
                }
                resultSet.close();
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        return counts;
    }

    @Override
    public void createMarkForStudent(int mark, int studentId) {
        LOG.trace("Starting tracing StudentOnCourseDAO#createMarkForStudent");
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.CREATE_MARK_FOR_STUDENT);
                statement.setInt(1, studentId);
                statement.setInt(2, mark);
                statement.execute();
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
    }
}
