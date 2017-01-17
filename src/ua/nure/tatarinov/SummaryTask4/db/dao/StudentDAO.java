package ua.nure.tatarinov.SummaryTask4.db.dao;

import ua.nure.tatarinov.SummaryTask4.db.dto.StudentDTO;

import java.util.List;

public interface StudentDAO {

    public List<StudentDTO> getAllStudents();

}