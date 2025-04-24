package com.werka.instagramlikewebapp.domain.daos.profile;

import com.werka.instagramlikewebapp.domain.daos.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    private UserProfile getUserProfileFromResultSet(ResultSet resultSet, String username) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        int postsQuantity = resultSet.getInt("posts_quantity");
        int followers = resultSet.getInt("followers");
        int following = resultSet.getInt("following");
        String bio = resultSet.getString("bio");
        return new UserProfile(id, userId, username, postsQuantity, followers, following, bio);
    }

}
