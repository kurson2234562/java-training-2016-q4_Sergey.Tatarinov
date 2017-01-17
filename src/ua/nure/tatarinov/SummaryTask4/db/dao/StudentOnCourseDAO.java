package ua.nure.tatarinov.SummaryTask4.db.dao;

import ua.nure.tatarinov.SummaryTask4.db.dto.StudentOnCourseDTO;

import java.util.List;

public interface StudentOnCourseDAO {

    public List<StudentOnCourseDTO> getAllStudentsOnCourse();

    public List<StudentOnCourseDTO> getCountStudentPerCourse();

    public void createMarkForStudent(int mark, int studentId);

}
