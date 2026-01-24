package Repository;

import Util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CommentRepository {

    public void addComment(long userId, long postId, String comment) {
        String sql = "INSERT INTO comments (user_id, post_id, comment) VALUES (?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ps.setLong(2, postId);
            ps.setString(3, comment);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getComments(long postId) {
        String sql = """
            SELECT c.id, u.email, c.comment
            FROM comments c
            JOIN users u ON c.user_id = u.id
            WHERE c.post_id = ?
        """;

        try {
            Connection con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, postId);
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteComment(long commentId, long userId) {
        String sql = "DELETE FROM comments WHERE id = ? AND user_id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, commentId);
            ps.setLong(2, userId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }
}
