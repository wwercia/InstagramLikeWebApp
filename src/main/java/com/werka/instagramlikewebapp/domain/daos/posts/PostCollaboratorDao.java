package com.werka.instagramlikewebapp.domain.daos.posts;

import com.werka.instagramlikewebapp.domain.daos.BaseDao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostCollaboratorDao extends BaseDao {

    public List<PostCollaborator> getTagsFromLastTwoMonths(int userId) {
        List<PostCollaborator> tags = new ArrayList<>();
        String sql = "SELECT * FROM post_collaborators WHERE user_collaborator_id = ? AND `date` >= DATE_SUB(CURDATE(), INTERVAL 2 MONTH) ORDER BY `date` DESC";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tags.add(getPostCollaboratorsFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Błąd przy pobieraniu tag'ów z dwóch ostatnich miesięcy", e);
        }
        return tags;
    }

    private PostCollaborator getPostCollaboratorsFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int postId = resultSet.getInt("post_id");
        int userCollaboratorId = resultSet.getInt("user_collaborator_id");
        Timestamp timestamp = resultSet.getTimestamp("date");
        LocalDateTime date = timestamp.toLocalDateTime();
        return new PostCollaborator(id, postId, userCollaboratorId, date);
    }

}
