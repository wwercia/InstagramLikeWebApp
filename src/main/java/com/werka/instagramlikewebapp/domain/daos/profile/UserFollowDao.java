package com.werka.instagramlikewebapp.domain.daos.profile;

import com.werka.instagramlikewebapp.domain.daos.posts.PostLike;
import com.werka.instagramlikewebapp.domain.dto.FollowerDto;
import com.werka.instagramlikewebapp.domain.daos.BaseDao;

import java.sql.*;
import java.time.LocalDateTime;
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
            statement.setInt(1, followerId);
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


    public List<FollowerDto> getFollowersFullData(int userId) {
        List<FollowerDto> followers = new ArrayList<>();

        final String sql = String.join(" ",
                "SELECT uf.follower_id AS id, u.username, up.profile_image_name",
                "FROM user_follow uf",
                "JOIN `user` u ON uf.follower_id = u.id",
                "LEFT JOIN user_profile up ON u.id = up.user_id",
                "WHERE uf.followed_id = ?"
        );


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String profileImageName = rs.getString("profile_image_name");
                followers.add(new FollowerDto(id, username, profileImageName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy pobieraniu danych followerów", e);
        }

        return followers;
    }

    public List<FollowerDto> getFollowingFullData(int userId) {
        List<FollowerDto> following = new ArrayList<>();

        final String sql =
                "SELECT uf.followed_id AS id, u.username, up.profile_image_name " +
                        "FROM user_follow uf " +
                        "JOIN user u ON uf.followed_id = u.id " +
                        "LEFT JOIN user_profile up ON u.id = up.user_id " +
                        "WHERE uf.follower_id = ?";


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String profileImageName = rs.getString("profile_image_name");
                following.add(new FollowerDto(id, username, profileImageName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy pobieraniu danych following", e);
        }

        return following;
    }

    public List<UserFollow> getFollowsFromLastTwoMonths(int userId) {
        List<UserFollow> follows = new ArrayList<>();
        String sql = "SELECT * FROM user_follow WHERE followed_id = ? AND `date` >= DATE_SUB(CURDATE(), INTERVAL 2 MONTH) ORDER BY `date` DESC";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                follows.add(getUserFollowFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy pobieraniu like'ów z dwóch ostatnich miesięcy", e);
        }
        return follows;
    }

    private UserFollow getUserFollowFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int followerId = resultSet.getInt("follower_id");
        int followedId = resultSet.getInt("followed_id");
        Timestamp timestamp = resultSet.getTimestamp("date");
        LocalDateTime addedAt = timestamp.toLocalDateTime();
        return new UserFollow(id, followerId, followedId, addedAt);
    }

}
