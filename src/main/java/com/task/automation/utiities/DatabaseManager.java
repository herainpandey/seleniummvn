package com.task.automation.utiities;

import java.sql.*;

public class DatabaseManager {
private static Connection conn = null;
private static void getDBConnection() throws SQLException {

    conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER_NAME, Constants.DB_PASSWORD);

    if(!conn.isClosed()){
        System.out.println("We have successfully connected to database");
    }
}

public static ResultSet getData(String sql) throws SQLException {
    getDBConnection();
    Statement st = conn.createStatement();

    ResultSet result = st.executeQuery(sql);
    return  result;
}

}
