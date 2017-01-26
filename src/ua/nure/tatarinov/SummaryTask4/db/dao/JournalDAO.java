package ua.nure.tatarinov.SummaryTask4.db.dao;

/**
 * Basic interface for all JournalDAO.
 *
 * @author S. Tatarinov
 */
public interface JournalDAO {

    /**
     * Update a student mark at the specified id
     * @param id
     * @param mark
     */
    public void updateJournal(int id, int mark);

}
