package ua.nure.tatarinov.SummaryTask4.db.dao.derby;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dao.CourseDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DerbyCourseDAO implements CourseDAO {

    public static final Logger LOG = Logger.getLogger(DerbyCourseDAO.class);

    @Override
    public void createCourse(String name, int duration, int theme, int lecturer, int status) {
        LOG.trace("Starting tracing DerbyCourseDAO#createCourse");
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.CREATE_COURSE, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, name);
                statement.setInt(2, duration);
                statement.setInt(3, theme);
                statement.setInt(4, lecturer);
                statement.setInt(5, status);
                statement.executeUpdate();
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void updateCourse(String name, int duration, int theme, int lecturer, int status, int id) {
        LOG.trace("Starting tracing DerbyCourseDAO#updateCourse");
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.UPDATE_COURSE, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, name);
                statement.setInt(2, duration);
                statement.setInt(3, theme);
                statement.setInt(4, lecturer);
                statement.setInt(5, status);
                statement.setInt(6, id);
                statement.executeUpdate();
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void deleteCourseByIdCourse(int id) {
        LOG.trace("Starting tracing DerbyCourseDAO#deleteCourseByIdCourse");
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.DELETE_COURSE, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, id);
                statement.executeUpdate();
                connection.setAutoCommit(false);
            }
        }catch (SQLException e){
            LOG.error(e.getLocalizedMessage());
        }
    }

    @Override
    public CourseDTO getCourseByIdCourse(int id) {
        LOG.trace("Starting tracing DerbyCourseDAO#getCourseByIdCourse");
        CourseDTO course = new CourseDTO();
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.SELECT_DEFINITE_COURSE);
                connection.setAutoCommit(false);
                if (id != -1) {
                    course = new CourseDTO();
                    statement.setInt(1, id);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        course = new CourseDTO(resultSet.getInt("id_course"), resultSet.getString("name_course"),
                                resultSet.getInt("duration"), resultSet.getInt("id_theme"),
                                resultSet.getInt("id_lecturer"), resultSet.getInt("id_status"));
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage());
        }
        return course;
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        LOG.trace("Starting tracing DerbyStatusDAO#getAllStatuses");
        List<CourseDTO> courses = new ArrayList<>();
        CourseDTO course;

        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.SELECT_COURSES);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    course = new CourseDTO(resultSet.getInt("id_course"),
                            resultSet.getString("name_course"), resultSet.getInt("duration"),
                            resultSet.getInt("id_theme"), resultSet.getInt("id_lecturer"),
                            resultSet.getInt("id_status"));
                    courses.add(course);
                }
                resultSet.close();

            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        return courses;
    }
}