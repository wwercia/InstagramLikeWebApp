package com.werka.instagramlikewebapp.domain.daos.profile;

import com.werka.instagramlikewebapp.domain.daos.BaseDao;

import java.sql.*;

public class UserProfileDao extends BaseDao {

    public UserProfile getUserProfileById(int userId, String username) {
        final String sql = "SELECT * FROM user_profile WHERE user_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return getUserProfileFromResultSet(resultSet, username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveNewProfile(int userId) {
        final String sql = "INSERT INTO user_profile (user_id, posts_quantity, followers, following, bio, profile_image_name) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, userId);
            statement.setInt(2, 0);
            statement.setInt(3, 0);
            statement.setInt(4, 0);
            statement.setString(5, "bio");
            statement.setString(6, "");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy zapisie posta", e);
        }
    }

    public void updateBio(String bio, int userId) {
        final String sql = "UPDATE user_profile SET `bio` = ? WHERE (`user_id` = ?);";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bio);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveNewProfileImage(String imageName, int userId) {
        System.out.println(imageName);
        final String sql = "UPDATE user_profile SET `profile_image_name` = ? WHERE (`user_id` = ?);";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, imageName);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private UserProfile getUserProfileFromResultSet(ResultSet resultSet, String username) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        int postsQuantity = resultSet.getInt("posts_quantity");
        int followers = resultSet.getInt("followers");
        int following = resultSet.getInt("following");
        String bio = resultSet.getString("bio");
        String profileImageName = resultSet.getString("profile_image_name");
        return new UserProfile(id, userId, username, postsQuantity, followers, following, bio, profileImageName);
    }

}
