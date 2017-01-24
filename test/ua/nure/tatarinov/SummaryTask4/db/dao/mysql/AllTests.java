package ua.nure.tatarinov.SummaryTask4.db.dao.mysql;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TestMySQLUserDAO.class, TestMySQLLecturerDAO.class, TestMySQLStudentDAO.class,
        TestMySQLCourseDAO.class, TestMySQLJournalDAO.class, TestMySQLStatusDAO.class,
        TestMySQLStudentOnCourseDAO.class, TestMySQLThemeDAO.class})
public class AllTests {

}
