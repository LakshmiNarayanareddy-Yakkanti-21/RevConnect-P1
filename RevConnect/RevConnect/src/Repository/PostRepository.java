package Repository;

import Models.Post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Util.ConnectionManager;


public class PostRepository {

    public boolean create(long userId, String content) {
        String sql = "INSERT INTO posts(user_id, content) VALUES (?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ps.setString(2, content);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Post> getByUser(long userId) {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE user_id=?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Post(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("content")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public boolean delete(long postId, long userId) {
        String sql = "DELETE FROM posts WHERE id=? AND user_id=?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, postId);
            ps.setLong(2, userId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
