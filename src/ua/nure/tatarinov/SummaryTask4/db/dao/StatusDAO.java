package ua.nure.tatarinov.SummaryTask4.db.dao;

import ua.nure.tatarinov.SummaryTask4.db.dto.StatusDTO;

import java.util.List;

public interface StatusDAO {
    public List<StatusDTO> getAllStatuses();
}
