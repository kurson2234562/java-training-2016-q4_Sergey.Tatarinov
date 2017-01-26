package ua.nure.tatarinov.SummaryTask4.db.dao;

import ua.nure.tatarinov.SummaryTask4.db.dto.LecturerDTO;

import java.util.List;

/**
 * Basic interface for all LecturerDAO.
 *
 * @author S. Tatarinov
 */
public interface LecturerDAO {

    /**
     * @return List of all lecturers
     */
    public List<LecturerDTO> getAllLecturers();

    /**
     * Create a lecturer at the specified id
     * @param surname
     * @param name
     * @param patronymic
     * @param idUser
     * @return
     */
    public int createLecturer(String surname, String name, String patronymic, int idUser);

    /**
     * Change fastened lecturer for the course
     * @param id
     * @param idCourse
     */
    public void changeLecturer(int id, int idCourse);

    /**
     * Looking for a lecturer at the specified id
     * @param search
     * @return
     */
    public List<LecturerDTO> findLecturersByString(String search);

    /**
     * Update information about a lecturer at the specified id
     * @param idUser
     * @param surname
     * @param name
     * @param patronymic
     * @param login
     * @param email
     */
    public void updateLecturerById(int idUser, String surname, String name, String patronymic, String login, String email);
}
