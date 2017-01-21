package ua.nure.tatarinov.SummaryTask4.db.dto;


import java.io.Serializable;

public class CourseDTO implements Serializable{
    private static final long serialVersionUID = -5527657822689332544L;
    private int idCourse;
    private String nameCourse;
    private int duration;
    private int idTheme;
    private int idLecturer;
    private int idStatus;

    public CourseDTO() {
    }

    public CourseDTO(int idCourse, String nameCourse, int duration, int idTheme, int idLecturer, int idStatus) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.duration = duration;
        this.idTheme = idTheme;
        this.idLecturer = idLecturer;
        this.idStatus = idStatus;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "idCourse=" + idCourse +
                ", nameCourse='" + nameCourse + '\'' +
                ", duration=" + duration +
                ", idTheme=" + idTheme +
                ", idLecturer=" + idLecturer +
                ", idStatus=" + idStatus +
                '}';
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(int idTheme) {
        this.idTheme = idTheme;
    }

    public int getIdLecturer() {
        return idLecturer;
    }

    public void setIdLecturer(int idLecturer) {
        this.idLecturer = idLecturer;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }
}
