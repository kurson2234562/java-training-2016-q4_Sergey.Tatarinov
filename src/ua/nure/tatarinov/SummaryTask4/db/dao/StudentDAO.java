package ua.nure.tatarinov.SummaryTask4.db.dao;

import ua.nure.tatarinov.SummaryTask4.db.dto.StudentDTO;

import java.util.List;

public interface StudentDAO {

    public List<StudentDTO> getAllStudents();

    public List<StudentDTO> findStudentsByString(String searchResult);

    public StudentDTO findStudentByIdUser(int id);

    public List<Integer> getStudentMarksById(int id);
}