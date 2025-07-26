package com.werka.instagramlikewebapp.domain.daos.user;

import com.werka.instagramlikewebapp.domain.daos.BaseDao;

import java.sql.*;

public class UserDao extends BaseDao {

    public User saveUser(String email, String password, String username, String firstName, String lastName, int age) {
        final String sql = "INSERT INTO user (email, password, username, firstName, lastName, age) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, username);
            statement.setString(4, firstName);
            statement.setString(5, lastName);
            statement.setInt(6, age);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                return new User(userId, email, password, username, firstName, lastName, age);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return null;
    }

    private int getUserIdByEmail(String email) {
        final String sql = "SELECT id FROM user WHERE `email` = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public int getUserIdByUserName(String username) {
        final String sql = "SELECT id FROM user WHERE `username` = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public boolean isEmailFree(String email) {

        final String sql = "SELECT email FROM user WHERE `email` = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public User getUserIfDataIsCorrect(String email, String password) {

        final String sql = "SELECT * FROM user WHERE `email`=? AND password=? ";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            User user = null;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String emaill = resultSet.getString("email");
                String passwordd = resultSet.getString("password");
                String username = resultSet.getString("username");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                user = new User(id, emaill, passwordd, username, firstName, lastName, age);
            }

            if (user != null) {
                return user;
            }
            System.out.println("cos nie tak");
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsernameById(int userId) {
        final String sql = "SELECT username FROM user WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString("username");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUsername(String username, int userId) {
        final String sql = "UPDATE user SET `username` = ? WHERE (`id` = ?);";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
