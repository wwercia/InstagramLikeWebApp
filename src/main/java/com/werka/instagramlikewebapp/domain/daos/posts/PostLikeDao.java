package com.werka.instagramlikewebapp.domain.daos.posts;

import com.werka.instagramlikewebapp.config.DataHelper;
import com.werka.instagramlikewebapp.domain.daos.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostLikeDao extends BaseDao {

    public boolean isPostLikedByUser(int postId) {
        String sql = "SELECT * FROM post_like WHERE user_id = ? AND post_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, DataHelper.getUser().getId());
            statement.setInt(2, postId);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd w isPostLikedByUser() w PostLikeDao", e);
        }
    }

    public void saveLikeToPostLikes(int postId) {
        String sql = "INSERT INTO post_like (user_id, post_id) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, DataHelper.getUser().getId());
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

}
