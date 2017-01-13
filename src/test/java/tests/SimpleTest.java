package tests;

import com.querydsl.sql.*;
import org.junit.Test;
import querydsl.QEmployee;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SimpleTest {

    @Test
    public void simpleTest() throws Exception {

        Class.forName("org.sqlite.JDBC");
        Connection dbConnection = DriverManager.getConnection("jdbc:sqlite:test.db");

        SQLTemplates templates = new SQLiteTemplates();
        Configuration configuration = new Configuration(templates);

        SQLQuery query = new SQLQuery(dbConnection, configuration);

        QEmployee employee = new QEmployee("e");
        List<String> lastNames = query.select(employee.lastname).from(employee).fetch();

        dbConnection.close();

        assertTrue(lastNames.contains("Mitchell"));
    }

}
