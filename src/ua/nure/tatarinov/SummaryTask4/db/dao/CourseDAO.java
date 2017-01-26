package ua.nure.tatarinov.SummaryTask4.db.dao;

import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;

import java.util.List;

/**
 * Basic interface for all CourseDAO.
 *
 * @author S. Tatarinov
 */
public interface CourseDAO {

    /**
     * Create a course at the specified id
     * @param name
     * @param duration
     * @param theme
     * @param lecturer
     * @param status
     */
    public void createCourse(String name, int duration, int theme, int lecturer, int status);

    /**
     * Update a course at the specified id
     * @param name
     * @param duration
     * @param theme
     * @param lecturer
     * @param status
     * @param id
     */
    public void updateCourseById(String name, int duration, int theme, int lecturer, int status, int id);

    /**
     * Delete a course at the specified id
     * @param id
     */
    public void deleteCourseByIdCourse(int id);

    /**
     * Looking for a course at the specified id
     * @param id
     * @return courses with specified id
     */
    public CourseDTO getCourseByIdCourse(int id);

    /**
     * @return List of all courses
     */
    public List<CourseDTO> getAllCourses();

    /**
     * Looking for a courses by specified string
     * @param searchResult
     * @return List of courses
     */
    public List<CourseDTO> findCourseByString(String searchResult);

    /**
     * Search for user all courses for which he wasn't registered
     * @param id
     * @return List of courses
     */
    public List<CourseDTO> findAllCoursesThatUserNotRegistered(int id);

}
