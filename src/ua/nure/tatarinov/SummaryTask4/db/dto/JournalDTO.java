package ua.nure.tatarinov.SummaryTask4.db.dto;

import java.io.Serializable;

/**
 * Data transfer object for journal that we receive from DB.
 * @author S. Tatarinov
 */
public class JournalDTO implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 5563653254994171031L;

    private int idStudentCourse;
    private int mark;

    /**
     * Default constructor for journal
     */
    public JournalDTO() {
    }

    /**
     * Full constructor for a journal
     * @param idStudentCourse id of studentcourse
     * @param mark mark student
     */
    public JournalDTO(int idStudentCourse, int mark) {
        this.idStudentCourse = idStudentCourse;
        this.mark = mark;
    }

    /**
     * @return the id studentoncorse
     */
    public int getIdStudentCourse() {
        return idStudentCourse;
    }

    /**
     * @param idStudentCourse the id studentcourse to set
     */
    public void setIdStudentCourse(int idStudentCourse) {
        this.idStudentCourse = idStudentCourse;
    }

    /**
     * @return the mark of student
     */
    public int getMark() {
        return mark;
    }

    /**
     * @param mark the mark of student to set
     */
    public void setMark(int mark) {
        this.mark = mark;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "JournalDTO{" +
                "idStudentCourse=" + idStudentCourse +
                ", mark=" + mark +
                '}';
    }
}
