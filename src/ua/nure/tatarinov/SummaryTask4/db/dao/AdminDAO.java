package ua.nure.tatarinov.SummaryTask4.db.dao;

/**
 * Basic interface for all AdminDAO.
 *
 * @author S. Tatarinov
 */
public interface AdminDAO {

    /**
     * Update information about a admin at the specified id
     * @param idUser id user
     * @param surname surname of user
     * @param name name of user
     * @param patronymic patronymic of user
     * @param login login of user
     * @param email email of user
     */
    public void updateAdminById(int idUser, String surname, String name, String patronymic, String login, String email);

    public String selectTopMark();

    public String selectAvgMark();

}
