package ua.nure.tatarinov.SummaryTask4.db.dto;

import java.io.Serializable;

/**
 * Data transfer object for lecturer that we receive from DB.
 * @author S. Tatarinov
 */
public class LecturerDTO implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 8839698692467044620L;

    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private int idUser;

    /**
     * Default constructor for lecturer
     */
    public LecturerDTO(){

    }

    /**
     * Full constructor for a lecturer
     * @param id lecturer id
     * @param surname surname of lecturer
     * @param name name of lecturer
     * @param patronymic patronymic of lecturer
     * @param idUser user id lecturer
     */
    public LecturerDTO(int id, String surname, String name, String patronymic, int idUser) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.idUser = idUser;
    }

    /**
     * @return lecturer id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id lecturer to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return surname of lecturer
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname of lecturer to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return name of lecturer
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name of lecturer to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return patronymic of lecturer
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * @param patronymic the patronymic of lecturer to set
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * @return id user lecturer
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the id user lecturer to set
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
}