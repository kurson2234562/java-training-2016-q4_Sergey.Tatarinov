package ua.nure.tatarinov.SummaryTask4.db.dao.derby;

import org.apache.log4j.Logger;
import ua.nure.tatarinov.SummaryTask4.db.Query;
import ua.nure.tatarinov.SummaryTask4.db.dao.ConnectionPool;
import ua.nure.tatarinov.SummaryTask4.db.dao.UserDAO;
import ua.nure.tatarinov.SummaryTask4.db.dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DerbyUserDAO extends UserDTO implements UserDAO {

    public static final Logger LOG = Logger.getLogger(DerbyUserDAO.class);

    @Override
    public void lockUserById(int id, int state) {
        LOG.trace("Start tracing DerbyUserDAO#lockUserById");
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if ((connection != null) && (state != -1)) {
                PreparedStatement statement = connection.prepareStatement(Query.CHANGE_STATE_USER, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, state);
                statement.setInt(2, id);
                statement.executeUpdate();
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            LOG.error(e.getLocalizedMessage());
        }
    }

    public UserDTO findUserByLogin(String login) {
        LOG.trace("Start tracing DerbyUserDAO#findUserByLogin");
        UserDTO user = null;
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE LOGIN = ?");
                connection.setAutoCommit(false);
                statement.setString(1, login);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    user = new UserDTO();
                    user.setIdUser(resultSet.getInt("id_user"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRoleId(resultSet.getInt("id_role"));
                    user.setStateId(resultSet.getInt("id_state"));
                    user.setEmail(resultSet.getString("email"));
                }
                resultSet.close();
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        return user;
    }

    @Override
    public UserDTO createUser(String login, String password) {
        UserDTO user = null;
        int id = -1;
        LOG.trace("Start tracing DerbyUserDAO#createUser");

        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.CREATE_USER, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, login);
                statement.setString(2, password);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement stmt = connection.prepareStatement(Query.SELECT_LAST_USER_ID);
                stmt.execute();
                ResultSet resultSet = stmt.getResultSet();
                if (resultSet.next()) {
                    id = resultSet.getInt("1");
                }
                connection.setAutoCommit(false);
                resultSet.close();
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }
        user = new UserDTO(id, login, password, 2, 1);
        return user;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        LOG.trace("Start tracing DerbyUserDAO#getAllUsers");
        List<UserDTO> users = new ArrayList<>();
        UserDTO user;

        try (Connection connection = ConnectionPool.getConnetcion()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(Query.SELECT_ALL_USERS);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    user = new UserDTO(resultSet.getInt("id_user"), resultSet.getString("login"),
                            resultSet.getString("password"), resultSet.getString("email"),
                            resultSet.getInt("id_role"), resultSet.getInt("id_state"));
                    users.add(user);
                }
                resultSet.close();
            }
        } catch (SQLException ex) {
            LOG.info(ex.getLocalizedMessage());
        }

        return users;
    }

    public void setNewPassword(int id, String password){
        LOG.trace("Start tracing DerbyUserDAO#setNewPassword");
        try (Connection connection = ConnectionPool.getConnetcion()){
            if (connection!=null){
                PreparedStatement statement = connection.prepareStatement(Query.UPDATE_PASSWORD);
                statement.setString(1 ,password);
                statement.setInt(2, id);
                statement.executeUpdate();
                connection.setAutoCommit(false);
            }
        }catch (SQLException e){
            LOG.error(e.getLocalizedMessage());
        }
    }
}