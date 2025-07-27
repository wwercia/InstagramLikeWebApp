package com.werka.instagramlikewebapp.domain.daos.profile;

import com.werka.instagramlikewebapp.domain.daos.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFollowDao extends BaseDao {

    public boolean isUserFollowed(int followerId, int followedId) {
        String sql = "SELECT * FROM user_follow WHERE follower_id = ? AND followed_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, followerId);
            statement.setInt(2, followedId);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd w isUserFollowed() w UserFollowDao", e);
        }
    }

    public void follow(int followerId, int followedId) {
        String sql = "INSERT INTO user_follow (follower_id, followed_id) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,followerId);
            statement.setInt(2, followedId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd w follow()", e);
        }
    }

    public void unfollow(int followerId, int followedId) {
        String sql = "DELETE FROM user_follow WHERE follower_id = ? AND followed_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, followerId);
            statement.setInt(2, followedId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd w unfollow()", e);
        }
    }

    public List<Integer> getFollowersIds(int userId) {
        List<Integer> followersIds = new ArrayList<>();
        final String sql = "SELECT * FROM user_follow WHERE `followed_id` = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                followersIds.add(resultSet.getInt("follower_id"));
            }
            return followersIds;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy pobieraniu id followerów po id użytkownika", e);
        }
    }

}
