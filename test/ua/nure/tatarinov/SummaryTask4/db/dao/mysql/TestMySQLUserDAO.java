package ua.nure.tatarinov.SummaryTask4.db.dao.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.apache.derby.jdbc.ClientDataSource;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.naming.java.javaURLContextFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.nure.tatarinov.SummaryTask4.db.dto.CourseDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.StudentOnCourseDTO;
import ua.nure.tatarinov.SummaryTask4.db.dto.UserDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.nure.tatarinov.SummaryTask4.core.Utils.encrypt;

public class TestMySQLUserDAO {

    UserDTO userDTO = new UserDTO();
    static Context context;
    static ClientDataSource ds;

    public static Logger LOG = Logger.getLogger(TestMySQLUserDAO.class);

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

    @Before
    public void setUp() throws Exception {
        userDTO.setIdUser(1);
        userDTO.setLogin("root");
        userDTO.setEmail("serhii.tatarinov@nure.ua");
        userDTO.setPassword(encrypt("root"));
        userDTO.setStateId(1);
        userDTO.setRoleId(0);
    }


    @Test
    public void lockUserById() throws Exception {
        new MySQLUserDAO().lockUserById(1, 0);
        assertEquals(0, new MySQLUserDAO().getAllUsers().get(0).getStateId());
    }

    @Test
    public void findUserByLogin() throws Exception {
        UserDTO user = new MySQLUserDAO().findUserByLogin("root");
        assertEquals(user.getLogin(), userDTO.getLogin());
    }

    @Test
    public void createUser() throws Exception {
        UserDTO user = new MySQLUserDAO().createUser("log", "pass");
        assertEquals(user.getLogin(), "log");
    }

    @Test
    public void getAllUsers() throws Exception {
        List<UserDTO> users = new MySQLUserDAO().getAllUsers();
        assertNotNull(users);
    }

    @Test
    public void registerUserOnCourse() throws Exception {
        new MySQLUserDAO().registerUserOnCourse(3, 4);
        CourseDTO course = new MySQLCourseDAO().getCourseByIdCourse(3);
        List<StudentOnCourseDTO> list = new MySQLStudentOnCourseDAO().getAllStudentsOnCourse();
        assertEquals(list.get(list.size() - 1).getIdCourse(), 4);
    }

    @Test
    public void setNewPassword() throws Exception {
        new MySQLUserDAO().setNewPassword(1, encrypt("pass"));
        assertEquals(encrypt("pass"), new MySQLUserDAO().findUserByLogin("root").getPassword());
    }

}