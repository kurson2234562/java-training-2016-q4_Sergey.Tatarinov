package ua.nure.tatarinov.SummaryTask4.db.dao;

import ua.nure.tatarinov.SummaryTask4.db.dto.ThemeDTO;

import java.util.List;

/**
 * Basic interface for all ThemeDAO.
 *
 * @author S. Tatarinov
 */
public interface ThemeDAO {

    /**
     * @return List of all themes
     */
    public List<ThemeDTO> getAllThemes();
}
