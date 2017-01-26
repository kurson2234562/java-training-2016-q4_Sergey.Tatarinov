package ua.nure.tatarinov.SummaryTask4.db.dto;

import java.io.Serializable;

/**
 * Data transfer object for user that we receive from DB.
 * @author S. Tatarinov
 */
public class UserDTO implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 8218802710491337849L;

    private int idUser;
    private String login;
    private String password;
    private String email;
    private int roleId = -1;
    private int stateId = 1;

    /**
     * Default constructor for user
     */
    public UserDTO() {

    }

    /**
     * Specific constructor for a user
     * @param login login of user
     * @param password password of user
     * @param roleId user role
     */
    public UserDTO(String login, String password, int roleId) {
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    /**
     * Specific constructor for a user
     * @param login login of user
     * @param password password of user
     * @param roleId user role
     * @param stateId user state
     */
    public UserDTO(String login, String password, int roleId, int stateId) {
        this.login = login;
        this.password = password;
        this.roleId = roleId;
        this.stateId = stateId;
    }

    /**
     * Specific constructor for a user
     * @param idUser id user
     * @param login login of user
     * @param password password of user
     * @param roleId user role
     * @param stateId user state
     */
    public UserDTO(int idUser, String login, String password, int roleId, int stateId) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.roleId = roleId;
        this.stateId = stateId;
    }

    /**
     * Full constructor for a user
     * @param idUser id user
     * @param login login of user
     * @param password password of user
     * @param email email of user
     * @param roleId user role
     * @param stateId user state
     */
    public UserDTO(int idUser, String login, String password, String email, int roleId, int stateId) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
        this.stateId = stateId;
    }

    /**
     * @return the user id
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

    /**
     * @return the login of user
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login of user to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password of user
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password of user to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the id role of user
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the id role of user to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the email of user
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email of user to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the id state of user
     */
    public int getStateId() {
        return stateId;
    }

    /**
     * @param stateId the id state of user to set
     */
    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "UserDTO{" +
                "idUser=" + idUser +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roleId=" + roleId +
                ", stateId=" + stateId +
                '}';
    }
}
