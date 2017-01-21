package ua.nure.tatarinov.SummaryTask4.db.dto;

import java.io.Serializable;

public class StudentOnCourseDTO implements Serializable {

    private static final long serialVersionUID = 6572259975878240054L;
    private int idStudentCourse;
    private int idStudent;
    private int idCourse;

    public StudentOnCourseDTO() {
    }

    public StudentOnCourseDTO(int idStudentCourse, int idStudent, int idCourse) {
        this.idStudentCourse = idStudentCourse;
        this.idStudent = idStudent;
        this.idCourse = idCourse;
    }

    @Override
    public String toString() {
        return "StudentOnCourseDTO{" +
                "idStudentCourse=" + idStudentCourse +
                ", idStudent=" + idStudent +
                ", idCourse=" + idCourse +
                '}';
    }

    public int getIdStudentCourse() {
        return idStudentCourse;
    }

    public void setIdStudentCourse(int idStudentCourse) {
        this.idStudentCourse = idStudentCourse;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }
}
