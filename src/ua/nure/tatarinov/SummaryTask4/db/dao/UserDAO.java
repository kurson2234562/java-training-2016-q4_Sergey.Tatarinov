package ua.nure.tatarinov.SummaryTask4.db.dao;

import ua.nure.tatarinov.SummaryTask4.db.dto.UserDTO;

import java.util.List;

public interface UserDAO {

    public void lockUserById(int id, int state);

    public UserDTO findUserByLogin(String login);

    public UserDTO createUser(String login, String password);

    public List<UserDTO> getAllUsers();
}
