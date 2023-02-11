package org.newsportal.database.dao.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static Properties properties = new Properties();
    private static ConnectionPool connectionPool;
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static final int MAX_CONNECTIONS = 10;

    static {
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/main/resources/database.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        URL = properties.getProperty("url");
        USER = properties.getProperty("username");
        PASSWORD = properties.getProperty("password");

        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private final BlockingQueue<Connection> blockingQueue = new ArrayBlockingQueue<>(MAX_CONNECTIONS);

    private ConnectionPool() {
        try {
            Class.forName(properties.getProperty("driverClassName"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            try {
                blockingQueue.put(createConnection());
            } catch (InterruptedException | SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Connection getConnection() throws InterruptedException {

        return blockingQueue.take();
    }

    public void returnConnection(Connection connection) throws InterruptedException {
        blockingQueue.put(connection);
    }

    public static ConnectionPool getConnectionPool() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }


//    public static void main(String[] args) {
//        User user = null;
//        try {
//            System.out.println(connection.getMetaData().getDriverName());
//
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from User where id = ?");
//            preparedStatement.setInt(1, 1);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                user = new User();
//                user.setId(resultSet.getLong("id"));
//                user.setUsername(resultSet.getString("username"));
//                user.setPassword(resultSet.getString("password"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(user);
//    }
}
