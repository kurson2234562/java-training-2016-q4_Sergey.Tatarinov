package ua.nure.tatarinov.SummaryTask4.db.dao;

import ua.nure.tatarinov.SummaryTask4.db.dto.LecturerDTO;

import java.util.List;

public interface LecturerDAO {
    public List<LecturerDTO> getAllLecturers();

    public int createLecturer(String surname, String name, String patronymic, int idUser);

    public void changeLecturer(int id, int idCourse);

    public List<LecturerDTO> findLecturersByString(String search);
}
