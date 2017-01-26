package ua.nure.tatarinov.SummaryTask4.db.dto;

import java.io.Serializable;

/**
 * Data transfer object for student on course that we receive from DB.
 * @author S. Tatarinov
 */
public class StudentOnCourseDTO implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6572259975878240054L;

    private int idStudentCourse;
    private int idStudent;
    private int idCourse;

    /**
     * Default constructor for student on course
     */
    public StudentOnCourseDTO() {
    }

    /**
     * Full constructor for a student on course
     * @param idStudentCourse
     * @param idStudent
     * @param idCourse
     */
    public StudentOnCourseDTO(int idStudentCourse, int idStudent, int idCourse) {
        this.idStudentCourse = idStudentCourse;
        this.idStudent = idStudent;
        this.idCourse = idCourse;
    }

    /**
     * @return id studentcourse
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
     * @return id student
     */
    public int getIdStudent() {
        return idStudent;
    }

    /**
     * @param idStudent the id student to set
     */
    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    /**
     * @return id course
     */
    public int getIdCourse() {
        return idCourse;
    }

    /**
     * @param idCourse the id course to set
     */
    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "StudentOnCourseDTO{" +
                "idStudentCourse=" + idStudentCourse +
                ", idStudent=" + idStudent +
                ", idCourse=" + idCourse +
                '}';
    }
}