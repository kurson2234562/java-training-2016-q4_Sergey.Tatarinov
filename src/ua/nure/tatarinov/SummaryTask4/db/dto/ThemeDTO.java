package ua.nure.tatarinov.SummaryTask4.db.dto;

import java.io.Serializable;

/**
 * Data transfer object for theme that we receive from DB.
 * @author S. Tatarinov
 */
public class ThemeDTO implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6674765258602087731L;

    private int idTheme;
    private String nameTheme;

    /**
     * Default constructor for theme
     */
    public ThemeDTO(){

    }

    /**
     * Full constructor for a theme
     * @param idTheme theme id
     * @param nameTheme name theme
     */
    public ThemeDTO(int idTheme, String nameTheme) {
        this.idTheme = idTheme;
        this.nameTheme = nameTheme;
    }

    /**
     * @return the id of theme
     */
    public int getIdTheme() {
        return idTheme;
    }

    /**
     * @param idTheme the id theme to set
     */
    public void setIdTheme(int idTheme) {
        this.idTheme = idTheme;
    }

    /**
     * @return the name of theme
     */
    public String getNameTheme() {
        return nameTheme;
    }

    /**
     * @param nameTheme the name of theme to set
     */
    public void setNameTheme(String nameTheme) {
        this.nameTheme = nameTheme;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "ThemeDTO{" +
                "idTheme=" + idTheme +
                ", nameTheme='" + nameTheme + '\'' +
                '}';
    }
}
