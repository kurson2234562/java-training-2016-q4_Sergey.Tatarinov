package ua.nure.tatarinov.SummaryTask4.db.dao;

import ua.nure.tatarinov.SummaryTask4.db.dto.UserDTO;

import java.util.List;

/**
 * Basic interface for all UserDAO.
 *
 * @author S. Tatarinov
 */
public interface UserDAO {

    /**
     * Lock a user by specified id
     * @param id
     * @param state
     */
    public void lockUserById(int id, int state);

    /**
     * Looking for a user by specified login
     * @param login
     * @return
     */
    public UserDTO findUserByLogin(String login);

    /**
     * Create a user at the specified id
     * @param login
     * @param password
     * @return
     */
    public UserDTO createUser(String login, String password);

    /**
     * @return List of all users
     */
    public List<UserDTO> getAllUsers();

    /**
     * Registered user on course by a specified id
     * @param id
     * @param idCourse
     */
    public void registerUserOnCourse(int id, int idCourse);

    /**
     * Set new password for a user at a specified String
     * @param id
     * @param password
     */
    public void setNewPassword(int id, String password);
}
