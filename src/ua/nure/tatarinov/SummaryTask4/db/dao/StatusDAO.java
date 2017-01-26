package ua.nure.tatarinov.SummaryTask4.db.dao;

import ua.nure.tatarinov.SummaryTask4.db.dto.StatusDTO;

import java.util.List;

/**
 * Basic interface for all StatusDAO.
 *
 * @author S. Tatarinov
 */
public interface StatusDAO {

    /**
     * @return List of all statuses
     */
    public List<StatusDTO> getAllStatuses();
}
