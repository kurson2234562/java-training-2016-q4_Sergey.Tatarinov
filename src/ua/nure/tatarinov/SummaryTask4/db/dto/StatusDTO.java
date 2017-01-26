package ua.nure.tatarinov.SummaryTask4.db.dto;

import java.io.Serializable;

/**
 * Data transfer object for status that we receive from DB.
 * @author S. Tatarinov
 */
public class StatusDTO implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 8848503175074357512L;

    private int idStatus;
    private String nameStatus;

    /**
     * Default constructor for status
     */
    public StatusDTO(){

    }

    /**
     * Full constructor for a status
     * @param idStatus status id
     * @param nameStatus name of status
     */
    public StatusDTO(int idStatus, String nameStatus) {
        this.idStatus = idStatus;
        this.nameStatus = nameStatus;
    }

    /**
     * @return the id of status
     */
    public int getIdStatus() {
        return idStatus;
    }

    /**
     * @param idStatus the id status to set
     */
    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    /**
     * @return the name of status
     */
    public String getNameStatus() {
        return nameStatus;
    }

    /**
     * @param nameStatus the name of status to set
     */
    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "StatusDTO{" +
                "idStatus=" + idStatus +
                ", nameStatus='" + nameStatus + '\'' +
                '}';
    }
}
