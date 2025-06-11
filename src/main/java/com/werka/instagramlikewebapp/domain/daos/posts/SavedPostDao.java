package com.werka.instagramlikewebapp.domain.daos.posts;

import com.werka.instagramlikewebapp.domain.daos.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SavedPostDao extends BaseDao {

    public List<Integer> getUserSavedPosts(int userId) {
        List<Integer> postIds = new ArrayList<>();
        final String sql = "SELECT * FROM saved_post WHERE user_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            while (resultSet.next()) {
                postIds.add(resultSet.getInt("post_id"));
            }
            return postIds;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy pobieraniu id posta po nazwie", e);
        }
    }

    public boolean isPostSavedInUserSavedPosts(int postId, int userId) {
        String sql = "SELECT * FROM saved_post WHERE user_id = ? AND post_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,userId);
            statement.setInt(2, postId);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy pobieraniu id posta po nazwie", e);
        }
    }

    public void savePostToUserSavedPosts(int postId, int userId) {
        String sql = "INSERT INTO saved_post (user_id, post_id) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, postId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy zapisie posta", e);
        }
    }

    public void removePostFromUserSavedPosts(int postId) {
        String sql = "DELETE FROM saved_post WHERE post_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, postId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd usunieciu posta z zapisanych", e);
        }
    }

    private SavedPost getPostFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        int postId = resultSet.getInt("post_id");
        return new SavedPost(id, userId, postId);
    }


}
