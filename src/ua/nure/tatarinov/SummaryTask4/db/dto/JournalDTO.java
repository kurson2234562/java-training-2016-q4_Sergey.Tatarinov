package ua.nure.tatarinov.SummaryTask4.db.dto;

/**
 * Created by kurson on 07.01.17.
 */
public class JournalDTO {
    private int idStudentCourse;
    private int mark;

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
