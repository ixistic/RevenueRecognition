package db;

/**
 * Created by ixistic on 1/24/2017 AD.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    public static DBHelper instance;
    public static final String SQL_URL = "jdbc:postgresql://localhost:5432/";

    public static final String DB_NAME = "Revenue_Recognition";
    public static final String DB_URL =  SQL_URL + DB_NAME;
    public static final String DB_USR = "";
    public static final String DB_PWD = "";

    public static Connection conn;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (instance == null)
            instance = new DBHelper();
        return instance;
    }

    public void establishConnection() {
        if (conn != null)
            return;
        Properties props = new Properties();
        props.put("user", DB_USR);
        props.put("password", DB_PWD);
        try {
            conn = DriverManager.getConnection(DB_URL, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }


    public PreparedStatement prepareStatement(String sql, int resultSetType) throws SQLException {
        return conn.prepareStatement(sql, resultSetType);
    }

}

