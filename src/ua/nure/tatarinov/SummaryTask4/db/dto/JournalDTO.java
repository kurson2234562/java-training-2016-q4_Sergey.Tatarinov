package ua.nure.tatarinov.SummaryTask4.db.dto;

import java.io.Serializable;

public class JournalDTO implements Serializable {
    private static final long serialVersionUID = 5563653254994171031L;
    private int idStudentCourse;
    private int mark;

    public JournalDTO(int idStudentCourse, int mark) {
        this.idStudentCourse = idStudentCourse;
        this.mark = mark;
    }

    public JournalDTO() {
    }

    @Override
    public String toString() {
        return "JournalDTO{" +
                "idStudentCourse=" + idStudentCourse +
                ", mark=" + mark +
                '}';
    }

    public int getIdStudentCourse() {
        return idStudentCourse;
    }

    public void setIdStudentCourse(int idStudentCourse) {
        this.idStudentCourse = idStudentCourse;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
