package ua.nure.tatarinov.SummaryTask4.db.dao;

/**
 * Basic interface for all AdminDAO.
 *
 * @author S. Tatarinov
 */
public interface AdminDAO {

    /**
     * Update information about a admin at the specified id
     * @param idUser
     * @param surname
     * @param name
     * @param patronymic
     * @param login
     * @param email
     */
    public void updateAdminById(int idUser, String surname, String name, String patronymic, String login, String email);

}
