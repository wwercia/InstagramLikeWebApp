package com.werka.instagramlikewebapp.domain.daos.posts;

import com.werka.instagramlikewebapp.domain.daos.BaseDao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostLikeDao extends BaseDao {

    public boolean isPostLikedByUser(int postId, int userId) {
        String sql = "SELECT * FROM post_like WHERE user_id = ? AND post_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, postId);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd w isPostLikedByUser() w PostLikeDao", e);
        }
    }

    public void saveLikeToPostLikes(int postId, int userId) {
        String sql = "INSERT INTO post_like (user_id, post_id) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,userId);
            statement.setInt(2, postId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd w saveLikeToPostLikes()", e);
        }
    }

    public void removeLikeFromPostLikes(int postId) {
        String sql = "DELETE FROM post_like WHERE post_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, postId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd w removeLikeFromPostLikes()", e);
        }
    }

    public List<PostLike> getLikesFromLastTwoMonths(List<Integer> postIds, int userId) {
        List<PostLike> likes = new ArrayList<>();
        for(int id : postIds) {
            String sql = "SELECT * FROM post_like WHERE `post_id` = ? AND user_id != ? AND `date` >= DATE_SUB(CURDATE(), INTERVAL 2 MONTH) ORDER BY `date` DESC";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.setInt(2, userId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    likes.add(getLikeFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Błąd przy pobieraniu like'ów z dwóch ostatnich miesięcy", e);
            }
        }
        return likes;
    }

    private PostLike getLikeFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        int postId = resultSet.getInt("post_id");
        Timestamp timestamp = resultSet.getTimestamp("date");
        LocalDateTime addedAt = timestamp.toLocalDateTime();
        return new PostLike(id, userId, postId, addedAt);
    }

}
