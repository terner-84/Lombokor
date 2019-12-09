
package cz.terner.lombokor.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import lombok.Data;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class SqliteConnector {
    public static final String DERBY_DRIVER = "org.sqlite.JDBC";
    public static final String JDBC_URL = "jdbc:sqlite:C:/data/sqlite-tools-win32-x86-3300100/sqlite-tools-win32-x86-3300100/terner.db";
    private static final String USER_NAME = "app";
    private static final String PASS = "app";
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        newWay();
    }
    
    @Data
    public static class Items {
        private String title;
        private String user;
    }
    
    private static void newWay() throws SQLException {
        Connection conn = null;
        QueryRunner queryRunner = new QueryRunner();
        DbUtils.loadDriver(DERBY_DRIVER);
        conn = DriverManager.getConnection(JDBC_URL);
        String sqliteQuery = "SELECT * FROM items";
        String createTable = "CREATE TABLE contacts (\n"
                + "    contact_id INTEGER PRIMARY KEY,\n"
                + "    first_name TEXT NOT NULL,\n"
                + "    last_name TEXT NOT NULL,\n"
                + "    email TEXT NOT NULL UNIQUE,\n"
                + "    phone TEXT NOT NULL UNIQUE\n"
                + ")";
        queryRunner.update(conn, createTable);
        //ResultSetHandler<Items> resultHandler = new BeanHandler<>(Items.class);
        //Items items = queryRunner.query(conn, sqliteQuery, resultHandler);
        
        //System.out.println(items);
        DbUtils.close(conn);
        
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
