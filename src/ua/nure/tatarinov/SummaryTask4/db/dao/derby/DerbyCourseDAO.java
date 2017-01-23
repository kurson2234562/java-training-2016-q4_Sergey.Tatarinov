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
                try (PreparedStatement statement = connection.prepareStatement(Query.CREATE_COURSE, Statement.RETURN_GENERATED_KEYS)) {
                    connection.setAutoCommit(false);
                    statement.setString(1, name);
                    statement.setInt(2, duration);
                    statement.setInt(3, theme);
                    statement.setInt(4, lecturer);
                    statement.setInt(5, status);
                    statement.executeUpdate();
                    connection.commit();
                } catch (SQLException ex) {
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
                }
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
                try (PreparedStatement statement = connection.prepareStatement(Query.UPDATE_COURSE, Statement.RETURN_GENERATED_KEYS)) {
                    connection.setAutoCommit(false);
                    statement.setString(1, name);
                    statement.setInt(2, duration);
                    statement.setInt(3, theme);
                    statement.setInt(4, lecturer);
                    statement.setInt(5, status);
                    statement.setInt(6, id);
                    statement.executeUpdate();
                    connection.commit();
                } catch (SQLException ex) {
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
                }
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
                try (PreparedStatement statement = connection.prepareStatement(Query.DELETE_COURSE, Statement.RETURN_GENERATED_KEYS)) {
                    connection.setAutoCommit(false);
                    statement.setInt(1, id);
                    statement.executeUpdate();
                    connection.commit();
                } catch (SQLException ex) {
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage());
        }
    }

    @Override
    public CourseDTO getCourseByIdCourse(int id) {
        LOG.trace("Starting tracing DerbyCourseDAO#getCourseByIdCourse");
        CourseDTO course = new CourseDTO();
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_ALL_DEFINITE_COURSE)) {
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
                    connection.commit();
                } catch (SQLException ex) {
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
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
                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_ALL_COURSES)) {
                    connection.setAutoCommit(false);
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
                    connection.commit();
                } catch (SQLException ex) {
                    LOG.error(ex.getLocalizedMessage());
                    connection.rollback();
                }
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        return courses;
    }

    @Override
    public List<CourseDTO> findCourseByString(String searchResult) {
        LOG.trace("Starting tracing DerbyStudentDAO#findCourseByString");
        List<CourseDTO> courses = new ArrayList<>();
        CourseDTO course;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.FIND_COURSE_BY_STRING)) {
                    connection.setAutoCommit(false);
                    statement.setString(1, "%" + searchResult + "%");
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        course = new CourseDTO(resultSet.getInt("id_course"), resultSet.getString("name_course"),
                                resultSet.getInt("duration"), resultSet.getInt("id_theme"),
                                resultSet.getInt("id_lecturer"), resultSet.getInt("id_status"));
                        courses.add(course);
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
        return courses;
    }

    @Override
    public List<CourseDTO> findAllCoursesThatUserNotRegistered(int id) {
        LOG.trace("Starting tracing DerbyStudentDAO#findAllCoursesThatUserNotRegistered");
        List<CourseDTO> courses = new ArrayList<>();
        CourseDTO course;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_COURSES_NOT_BEEN_REGISTERED)) {
                    connection.setAutoCommit(false);
                    statement.setInt(1, id);
                    statement.execute();
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        course = new CourseDTO(resultSet.getInt("id_course"), resultSet.getString("name_course"),
                                resultSet.getInt("duration"), resultSet.getInt("id_theme"),
                                resultSet.getInt("id_lecturer"), resultSet.getInt("id_status"));
                        courses.add(course);
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
        return courses;
    }
}