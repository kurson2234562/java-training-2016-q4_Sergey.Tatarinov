package ua.nure.tatarinov.SummaryTask4.db.dao.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.apache.log4j.PropertyConfigurator;
import org.apache.naming.java.javaURLContextFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.nure.tatarinov.SummaryTask4.db.dto.LecturerDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.UserDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestMySQLLecturerDAO {

    static Context context;

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
    public void getAllLecturers() throws Exception {
        List<LecturerDTO> lecturers = new MySQLLecturerDAO().getAllLecturers();
        assertNotNull(lecturers);
    }

    @Test
    public void createLecturer() throws Exception {
        int size = new MySQLLecturerDAO().getAllLecturers().size();
        UserDTO user = new MySQLUserDAO().createUser("loglog", "passpass");
        user.setIdUser(new MySQLUserDAO().getAllUsers().size());
        new MySQLLecturerDAO().createLecturer("Surname", "Name", "Patronymic", user.getIdUser());
        assertEquals(size + 1, new MySQLLecturerDAO().getAllLecturers().size());
    }

    @Test
    public void changeLecturer() throws Exception {
        new MySQLLecturerDAO().changeLecturer(4, 12);
        assertEquals(new MySQLCourseDAO().getCourseByIdCourse(12).getIdLecturer(), 4);
    }

    @Test
    public void findLecturersByString() throws Exception {
        List<LecturerDTO> lecturers = new MySQLLecturerDAO().findLecturersByString("Ko");
        assertEquals(lecturers.get(0).getSurname(),"Kolesnikov");
    }

    @Test
    public void updateLecturerById() throws Exception {
        new MySQLLecturerDAO().updateLecturerById(9, "Мищеряков", "Юрий", "Валентинович", "temp", "a@nure.ua");
        assertEquals(new MySQLUserDAO().getAllUsers().get(8).getEmail(), "a@nure.ua");
    }

}