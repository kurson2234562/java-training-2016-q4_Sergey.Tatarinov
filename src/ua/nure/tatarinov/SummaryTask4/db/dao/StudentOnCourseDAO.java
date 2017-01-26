package ua.nure.tatarinov.SummaryTask4.db.dao;

import ua.nure.tatarinov.SummaryTask4.db.dto.StudentOnCourseDTO;

import java.util.List;

/**
 * Basic interface for all StudentOnCourseDAO.
 *
 * @author S. Tatarinov
 */
public interface StudentOnCourseDAO {

    /**
     * @return List of all students on course
     */
    public List<StudentOnCourseDTO> getAllStudentsOnCourse();

    /**
     * @return a list of all counts of students
     */
    public List<StudentOnCourseDTO> getCountStudentPerCourse();

    /**
     * Create a mark for a student at the a specified id
     * @param mark
     * @param studentId
     */
    public void createMarkForStudent(int mark, int studentId);

}
