package ua.nure.tatarinov.SummaryTask4.db.dto;

import java.io.Serializable;

/**
 * Data transfer object for course that we receive from DB.
 * @author S. Tatarinov
 */
public class CourseDTO implements Serializable{

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -5527657822689332544L;

    private int idCourse;
    private String nameCourse;
    private int duration;
    private int idTheme;
    private int idLecturer;
    private int idStatus;

    /**
     * Default constructor for course
     */
    public CourseDTO() {
    }

    /**
     * Full constructor for a course
     * @param idCourse id of course
     * @param nameCourse name of course
     * @param duration duration course
     * @param idTheme theme of course
     * @param idLecturer lecturer of course
     * @param idStatus status of course
     */
    public CourseDTO(int idCourse, String nameCourse, int duration, int idTheme, int idLecturer, int idStatus) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.duration = duration;
        this.idTheme = idTheme;
        this.idLecturer = idLecturer;
        this.idStatus = idStatus;
    }

    /**
     * @return the id course
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

    /**
     * @return the name of course
     */
    public String getNameCourse() {
        return nameCourse;
    }

    /**
     * @param nameCourse the name course to set
     */
    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    /**
     * @return the duration of courses
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
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
     * @return the id of lecturer
     */
    public int getIdLecturer() {
        return idLecturer;
    }

    /**
     * @param idLecturer the id lecturer to set
     */
    public void setIdLecturer(int idLecturer) {
        this.idLecturer = idLecturer;
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

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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

}
