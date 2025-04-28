package com.werka.instagramlikewebapp.domain.daos.posts;

import com.werka.instagramlikewebapp.domain.daos.BaseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao extends BaseDao {

    // returns new image name
    public String savePost(int userId, String imageName, String description, String location, int likes) {

        final String sql = "INSERT INTO post (user_id, image_name, description, location, likes) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, userId);
            statement.setString(2, imageName);
            statement.setString(3, description);
            statement.setString(4, location);
            statement.setInt(5, likes);

            int rowsAffected =  statement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if(resultSet.next())
                    return "post" + resultSet.getInt(1);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Post> getPostsByUserId(int userId){
        List<Post> posts = new ArrayList<>();
        final String sql = "SELECT * FROM post WHERE `user_id` = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                posts.add(getPostFromResultSet(resultSet));
            }
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeImageName(String newName) {
        int postId = getPostIdFromPostName(newName);
        final String sql = "UPDATE post SET `image_name` = ? WHERE (`id` = ?);";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setInt(2, postId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getPostIdFromPostName(String postName) {
        return Integer.parseInt(postName.replace("post", ""));
    }

    private Post getPostFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        String imageName = resultSet.getString("image_name");
        String description = resultSet.getString("description");
        String location = resultSet.getString("location");
        int likes = resultSet.getInt("likes");
        return new Post(id, userId, imageName, description, location, likes);
    }

}
