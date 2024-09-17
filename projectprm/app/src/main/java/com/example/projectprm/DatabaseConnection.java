package com.example.projectprm;

import static android.content.ContentValues.TAG;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String serverName = "10.87.27.135"; // IP address that worked
    private static String instanceName = ""; // Điền tên instance nếu có, nếu không để trống
    private static String port = "1433";
    private static String database = "exe201c_v2";
    private static String username = "sa";
    private static String password = "1234567890";
    private static String url = String.format("jdbc:jtds:sqlserver://%s%s:%s/%s",
            serverName,
            instanceName.isEmpty() ? "" : "\\" + instanceName,
            port,
            database);

    private Connection connection = null;

    public Connection getConnection() {
        return connection;
    }

    public void connect() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Log.d(TAG, "Attempting to connect with URL: " + url);
            connection = DriverManager.getConnection(url, username, password);
            Log.d(TAG, "Connection successful");
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "JDBC driver not found", e);
        } catch (SQLException e) {
            Log.e(TAG, "SQL Exception occurred", e);
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                Log.d(TAG, "Connection closed successfully");
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error closing connection", e);
        }
    }
}
