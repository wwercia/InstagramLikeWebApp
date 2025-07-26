package com.werka.instagramlikewebapp.domain.daos.posts;

import com.werka.instagramlikewebapp.domain.daos.BaseDao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDao extends BaseDao {

    public void saveComment(int postId, String comment, int userId) {
        String sql = "INSERT INTO post_comment (post_id, user_id, comment) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, postId);
            statement.setInt(2, userId);
            statement.setString(3, comment);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd w saveComment()", e);
        }
    }

    public List<Comment> getCommentsByPostId(int postId) {
        List<Comment> comments = new ArrayList<>();
        final String sql = "SELECT * FROM post_comment WHERE `post_id` = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, postId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comments.add(getCommentFromResultSet(resultSet));
            }
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd w getCommentsByPostId()", e);
        }
    }

    public void removeComment(int commentId) {
        String sql = "DELETE FROM post_comment WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, commentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd usunieciu komentarza", e);
        }
    }

    private Comment getCommentFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int postId = resultSet.getInt("post_id");
        int userId = resultSet.getInt("user_id");
        String comment = resultSet.getString("comment");
        Timestamp timestamp = resultSet.getTimestamp("date");
        LocalDateTime addedAt = timestamp.toLocalDateTime();
        return new Comment(id, postId, userId, comment, addedAt);
    }

}
