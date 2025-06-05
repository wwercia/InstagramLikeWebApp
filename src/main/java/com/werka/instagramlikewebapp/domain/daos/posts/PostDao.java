package com.werka.instagramlikewebapp.domain.daos.posts;

import com.werka.instagramlikewebapp.config.DataHelper;
import com.werka.instagramlikewebapp.domain.daos.BaseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class PostDao extends BaseDao {

    public Integer savePost(int userId, String imageName, String description, String location, int likes, String imageExtension) {

        final String sql = "INSERT INTO post (user_id, image_name, description, location, likes, image_extension) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, userId);
            statement.setString(2, imageName);
            statement.setString(3, description);
            statement.setString(4, location);
            System.out.println(location);
            statement.setInt(5, likes);
            statement.setString(6, imageExtension);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy zapisie posta", e);
        }
    }

    public void saveCollaborators(int postId, List<String> collaborators) {
        List<Integer> userIds = getUserIdsByUsernames(collaborators);
        String sql = "INSERT INTO post_collaborators (post_id, user_collaborator_id) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Integer userId : userIds) {
                statement.setInt(1, postId);
                statement.setInt(2, userId);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy zapisie posta", e);
        }
    }

    private List<Integer> getUserIdsByUsernames(List<String> names) {
        List<Integer> userIds = new ArrayList<>();
        if (names == null || names.isEmpty()) return userIds;
        String sql = "SELECT id FROM user WHERE username IN (" + buildPlaceholders(names.size()) + ")";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < names.size(); i++) {
                statement.setString(i + 1, names.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userIds.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy zapisie posta", e);
        }
        return userIds;
    }

    private String buildPlaceholders(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("?");
            if (i < count - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public List<Post> getPostsByUserId(int userId) {
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
            e.printStackTrace();
            throw new RuntimeException("Błąd przy pobieraniu postów po id użytkownika", e);
        }
    }

    public List<Post> getUserTaggedPostsByUserId(int userId) {
        List<Integer> postIds = new ArrayList<>();
        final String sql = "SELECT * FROM post_collaborators WHERE `user_collaborator_id` = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                postIds.add(resultSet.getInt("post_id"));
            }
            return getPostsById(postIds);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy pobieraniu postów w których zotał oznaczony użytkownik po id użytkownika", e);
        }
    }

    private List<Post> getPostsById(List<Integer> postIds) {
        List<Post> posts = new ArrayList<>();
        if (postIds == null || postIds.isEmpty()) return new ArrayList<>();
        String sql = "SELECT * FROM post WHERE id IN (" + buildPlaceholders(postIds.size()) + ")";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < postIds.size(); i++) {
                statement.setInt(i + 1, postIds.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                posts.add(getPostFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy pobieraniu postó przez id", e);
        }
        return posts;
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

    public void changeImageExtension(String extension, int postId) {
        final String sql = "UPDATE post SET `image_extension` = ? WHERE (`id` = ?);";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, extension);
            statement.setInt(2, postId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Post getPostByImageName(String imageName) {
        final String sql = "SELECT * FROM post WHERE `image_name` = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, imageName);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return getPostFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy pobieraniu posta po nazwie", e);
        }
    }

    private int getPostIdFromPostName(String postName) {
        return Integer.parseInt(postName.replace("post", ""));
    }

    public int getPostIdByImageName(String imageName) {
        final String sql = "SELECT id FROM post WHERE image_name = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, imageName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy pobieraniu id posta po nazwie", e);
        }
    }

    public List<Post> getUserSavedPostsByPostIds(List<Integer> postIds) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM post WHERE id IN (" + buildPlaceholders(postIds.size()) + ")";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < postIds.size(); i++) {
                statement.setInt(i + 1, postIds.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                posts.add(getPostFromResultSet(resultSet));
            }

            Map<Integer, Post> postMap = posts.stream()
                    .collect(Collectors.toMap(Post::getId, post -> post));

            return postIds.stream()
                    .map(postMap::get)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy pobieraniu id posta po nazwie", e);
        }
    }

    public void increasePostsQuantity(){
        final String sql = "UPDATE user_profile SET posts_quantity = posts_quantity + 1 WHERE user_id = ?;";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, DataHelper.getUser().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Post getPostFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        String imageName = resultSet.getString("image_name");
        String description = resultSet.getString("description");
        String location = resultSet.getString("location");
        int likes = resultSet.getInt("likes");
        String imageExtension = resultSet.getString("image_extension");
        return new Post(id, userId, imageName, description, location, likes, imageExtension);
    }

}
