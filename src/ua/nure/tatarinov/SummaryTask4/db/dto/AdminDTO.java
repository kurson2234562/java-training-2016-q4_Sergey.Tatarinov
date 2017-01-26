package ua.nure.tatarinov.SummaryTask4.db.dto;

import java.io.Serializable;

/**
 * Data transfer object for admin that we receive from DB.
 * @author S. Tatarinov
 */
public class AdminDTO implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -5168076787036658486L;

    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private int idUser;

    /**
     * Default constructor for admin
     */
    public AdminDTO() {
    }

    /**
     * Full constructor for a admin
     * @param id id of admin
     * @param surname surname of admin
     * @param name name of admin
     * @param patronymic patronymic of admin
     * @param idUser user id admin
     */
    public AdminDTO(int id, String surname, String name, String patronymic, int idUser) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.idUser = idUser;
    }

    /**
     * @return id of admin
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id admin to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return surname of admin
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname of admin to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return name of admin
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name of admin to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return patronymic of admin
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * @param patronymic
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * @return user id of admin
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the id user to set
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "AdminDTO{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", idUser=" + idUser +
                '}';
    }

}
