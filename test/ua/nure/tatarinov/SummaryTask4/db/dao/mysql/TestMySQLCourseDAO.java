package ua.nure.tatarinov.SummaryTask4.db.dao.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.apache.log4j.PropertyConfigurator;
import org.apache.naming.java.javaURLContextFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestMySQLCourseDAO {

    CourseDTO course = new CourseDTO();
    static Context context;

    @Before
    public void setUp() throws Exception {
        course.setNameCourse("Java course");
        course.setDuration(10);
        course.setIdCourse(10);
        course.setIdLecturer(10);
        course.setIdStatus(1);
        course.setIdTheme(2);
    }

    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        PropertyConfigurator.configure("/home/kurson/SummaryTask4/web/WEB-INF/log4j.properties");
        ds.setURL("jdbc:mysql://localhost:3306/courses");
        ds.setUser("root");
        ds.setPassword("remdigga4237");
        DataSource dataSource = ds;
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, javaURLContextFactory.class.getName());
        context = new InitialContext();
        Context ctx = context.createSubcontext("java");
        ctx.createSubcontext("comp").createSubcontext("env").createSubcontext("jdbc")
                .bind("courses", dataSource);
        context.bind("java:", ctx);

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        context.destroySubcontext("java");
        context.unbind("java:");
        context.close();
    }

    @Test
    public void createCourse() throws Exception {
        new MySQLCourseDAO().createCourse("SQL+ course", 12, 1, 3, 2);
        assertEquals(new MySQLCourseDAO().getCourseByIdCourse(new MySQLCourseDAO().getAllCourses().size() + 1).getNameCourse(), "SQL+ course");
    }

    @Test
    public void updateCourse() throws Exception {
        new MySQLCourseDAO().updateCourse("SQL course", 10, 1, 4, 1, 6);
        assertEquals(new MySQLCourseDAO().getCourseByIdCourse(6).getNameCourse(), "SQL course");
    }

    @Test
    public void getCourseByIdCourse() throws Exception {
        CourseDTO courseDTO = new MySQLCourseDAO().getCourseByIdCourse(3);
        assertEquals(courseDTO.getDuration(), 15);
    }

    @Test
    public void getAllCourses() throws Exception {
        List<CourseDTO> list = new MySQLCourseDAO().getAllCourses();
        assertNotNull(list);
    }

    @Test
    public void findCourseByString() throws Exception {
        List<CourseDTO> courses = new MySQLCourseDAO().findCourseByString("Ruby");
        assertEquals(courses.get(0).getDuration(), 13);
    }

    @Test
    public void findAllCoursesThatUserNotRegistered() throws Exception {
        List<CourseDTO> list = new MySQLCourseDAO().findAllCoursesThatUserNotRegistered(5);
        assertEquals(list.get(2).getNameCourse(), "Kotlin developing");
    }

    @Test
    public void deleteCourseByIdCourse() throws Exception {
        new MySQLCourseDAO().deleteCourseByIdCourse(7);
        assertNull(new MySQLCourseDAO().getCourseByIdCourse(7).getNameCourse());
    }

}