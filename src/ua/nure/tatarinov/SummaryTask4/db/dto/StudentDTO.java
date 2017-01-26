package ua.nure.tatarinov.SummaryTask4.db.dto;

import java.io.Serializable;

/**
 * Data transfer object for student that we receive from DB.
 * @author S. Tatarinov
 */
public class StudentDTO implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -5205879381501085376L;

    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private int idUser;

    /**
     * Default constructor for student
     */
    public StudentDTO() {
    }

    /**
     * Full constructor for a student
     * @param id student id
     * @param surname surname of student
     * @param name name of student
     * @param patronymic patronymic of student
     * @param idUser user id of student
     */
    public StudentDTO(int id, String surname, String name, String patronymic, int idUser) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.idUser = idUser;
    }

    /**
     * @return id student
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id student to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return surname of student
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname of student to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return name of student
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name of student to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return patronymic of student
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * @param patronymic the patronymic of student to set
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * @return student's user id
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the student's id user to set
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}