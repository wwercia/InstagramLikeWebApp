package com.werka.instagramlikewebapp.domain.daos.posts;

import com.werka.instagramlikewebapp.config.DataHelper;
import com.werka.instagramlikewebapp.domain.daos.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao extends BaseDao {

    public void saveComment(int postId, String comment) {
        String sql = "INSERT INTO post_comment (post_id, username, comment) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, postId);
            statement.setString(2, DataHelper.getUser().getUsername());
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

    private Comment getCommentFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int postId = resultSet.getInt("post_id");
        String username = resultSet.getString("username");
        String comment = resultSet.getString("comment");
        return new Comment(id, postId, username, comment);
    }

}
