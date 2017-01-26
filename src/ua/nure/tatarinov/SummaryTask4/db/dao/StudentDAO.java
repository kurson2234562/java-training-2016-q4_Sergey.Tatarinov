package ua.nure.tatarinov.SummaryTask4.db.dao;

import ua.nure.tatarinov.SummaryTask4.db.dto.StudentDTO;

import java.util.List;

/**
 * Basic interface for all StudentDAO.
 *
 * @author S. Tatarinov
 */
public interface StudentDAO {

    /**
     * @return List of all students
     */
    public List<StudentDTO> getAllStudents();

    /**
     * Looking for a all students by specified string
     * @param searchResult
     * @return List of courses
     */
    public List<StudentDTO> findStudentsByString(String searchResult);

    /**
     * Looking for a student by specified id
     * @param id
     * @return
     */
    public StudentDTO findStudentByIdUser(int id);

    /**
     * @param id
     * @return a list of all student marks
     */
    public List<Integer> getStudentMarksById(int id);

    /**
     * Update information about a student at the specified id
     * @param idUser
     * @param surname
     * @param name
     * @param patronymic
     * @param login
     * @param email
     */
    public void updateStudentById (int idUser, String surname, String name, String patronymic, String login, String email);
}