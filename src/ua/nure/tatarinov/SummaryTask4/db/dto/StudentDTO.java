package ua.nure.tatarinov.SummaryTask4.db.dto;

public class StudentDTO {

    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private int idUser;

    public StudentDTO() {
    }

    public StudentDTO(int id, String surname, String name, String patronymic, int idUser) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "LecturerDTO{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", idUser=" + idUser +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

}
