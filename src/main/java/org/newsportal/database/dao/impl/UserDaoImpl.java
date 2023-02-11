package org.newsportal.database.dao.impl;

import org.newsportal.database.dao.UserDao;
import org.newsportal.database.dao.entity.User;
import org.newsportal.database.dao.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
//    private final Connection connection;

    public UserDaoImpl() {
        /*try {
            this.connection = ConnectionPool.getConnectionPool().getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public List<User> findAll() {
        List<User> usersList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection();
             ResultSet resultSet = connection.prepareStatement("select * from User").executeQuery()) {
            while (resultSet.next()) {
                usersList.add(new User(resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")));
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return usersList;
    }

    @Override
    public User getById(Long id) {
        User user = null;
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from User where id=?");) {

            preparedStatement.setInt(1, Math.toIntExact(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
            resultSet.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User getByUsername(String username) {
        User user = null;
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from User where username = ?")) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
            resultSet.close();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void updateUserById(Long id, User user) {

        try (Connection connection = ConnectionPool.getConnectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE User SET  username=?, password=? WHERE id=?")){

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, Math.toIntExact(id));
            preparedStatement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUserById(Long id) {
        try (Connection connection = ConnectionPool.getConnectionPool().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User WHERE id=?")){

            preparedStatement.setInt(1, Math.toIntExact(id));
            preparedStatement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.findAll());
        System.out.println(userDao.getById(1L));

    }
}
