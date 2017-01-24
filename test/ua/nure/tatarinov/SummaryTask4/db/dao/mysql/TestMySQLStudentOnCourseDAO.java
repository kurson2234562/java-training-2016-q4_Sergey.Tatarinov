package ua.nure.tatarinov.SummaryTask4.db.dao.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.apache.log4j.PropertyConfigurator;
import org.apache.naming.java.javaURLContextFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.nure.tatarinov.SummaryTask4.db.dto.StudentOnCourseDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestMySQLStudentOnCourseDAO {

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
    public void getAllStudentsOnCourse() throws Exception {
        List<StudentOnCourseDTO> list = new MySQLStudentOnCourseDAO().getAllStudentsOnCourse();
        assertNotNull(list);
    }

    @Test
    public void getCountStudentPerCourse() throws Exception {
        List<StudentOnCourseDTO> list = new MySQLStudentOnCourseDAO().getCountStudentPerCourse();
        assertNotNull(list);
    }

    @Test
    public void createMarkForStudent() throws Exception {
        new MySQLStudentOnCourseDAO().createMarkForStudent(90, 21);
        List<Integer> marks = new MySQLStudentDAO().getStudentMarksById(21);
        assertEquals(marks.get(marks.size() - 1).intValue(), 90);
    }

}