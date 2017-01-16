package ua.nure.tatarinov.SummaryTask4.db.dao;

import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;

import java.util.List;

public interface CourseDAO {

    public void createCourse(String name, int duration, int theme, int lecturer, int status);

    public void updateCourse(String name, int duration, int theme, int lecturer, int status, int id);

    public void deleteCourseByIdCourse(int id);

    public CourseDTO getCourseByIdCourse(int id);

    public List<CourseDTO> getAllCourses();

}
