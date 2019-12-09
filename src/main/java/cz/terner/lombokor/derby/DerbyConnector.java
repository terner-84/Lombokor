
package cz.terner.lombokor.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

public class DerbyConnector {
    public static final String DERBY_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    public static final String JDBC_URL = "jdbc:derby://localhost:1527/sample;create=true";
    private static final String USER_NAME = "app";
    private static final String PASS = "app";
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        getTda();
    }
    
    private static void newWay() throws SQLException {
        Connection conn = null;
        QueryRunner queryRunner = new QueryRunner();
        DbUtils.loadDriver(DERBY_DRIVER);
        conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASS);
        String derbySql = "INSERT INTO FERDA.BOUDA(DASS_ID, JMENA) VALUES (?, ?)";
        queryRunner.batch(conn, derbySql, 
                new Object[][] {
                    {100, "Karel"}, {101, "Peter"}
                });
        
    }
    
    private static void oldWay() throws ClassNotFoundException, SQLException {
        Class.forName(DERBY_DRIVER);
        Connection conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASS);
        PreparedStatement statement = conn.prepareStatement("CREATE SCHEMA ferdax");
        statement.executeUpdate();
    }
    
    private static Object[][] getTda() {
        Object[][] someTda = {{"100", "Karel", "4", true, 'O'}, {"P", "Peter", 3, 745154L, 'U'}};
        System.out.println(someTda[0][2]);
        return someTda;
    }
    
    
}
