package ua.nure.tatarinov.SummaryTask4.db.dto;

/**
 * UserDTO dto.
 * @author Sergey Tatarinov
 */
public class UserDTO {
    private int idUser;
    private String login;
    private String password;
    private String email;
    private int roleId = -1;
    private int stateId = 1;

    public UserDTO(int idUser, String login, String password, String email, int roleId, int stateId) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
        this.stateId = stateId;
    }

    public UserDTO(int idUser, String login, String password, int roleId, int stateId) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.roleId = roleId;
        this.stateId = stateId;
    }

    public UserDTO(String login, String password, int roleId, int stateId) {
        this.login = login;
        this.password = password;
        this.roleId = roleId;
        this.stateId = stateId;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public UserDTO(String login, String password, int roleId) {
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    public UserDTO() {

    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int idState) {
        this.stateId = idState;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
