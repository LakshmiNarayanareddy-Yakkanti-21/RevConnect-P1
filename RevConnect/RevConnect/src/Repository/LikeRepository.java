package Repository;

import Util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LikeRepository {

    public boolean likePost(long userId, long postId) {
        String sql = "INSERT INTO likes (user_id, post_id) VALUES (?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ps.setLong(2, postId);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            // Duplicate like or DB issue
            return false;
        }
    }

    public boolean unlikePost(long userId, long postId) {
        String sql = "DELETE FROM likes WHERE user_id = ? AND post_id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ps.setLong(2, postId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

}
