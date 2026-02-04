package Repository;

import Util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionRepository {

    public void sendRequest(long senderId, long receiverId) {
        String sql = """
            INSERT INTO connections (sender_id, receiver_id, status)
            VALUES (?, ?, 'PENDING')
        """;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, senderId);
            ps.setLong(2, receiverId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Request already exists or error");
        }
    }

    public ResultSet getPendingRequests(long userId) {
        String sql = """
            SELECT c.id, u.email
            FROM connections c
            JOIN users u ON c.sender_id = u.id
            WHERE c.receiver_id = ? AND c.status = 'PENDING'
        """;

        try {
            Connection con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, userId);
            return ps.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }

    public void updateStatus(long requestId, String status) {
        String sql = "UPDATE connections SET status=? WHERE id=?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setLong(2, requestId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getConnections(long userId) {
        String sql = """
            SELECT u.email
            FROM connections c
            JOIN users u
              ON (u.id = c.sender_id OR u.id = c.receiver_id)
            WHERE c.status='ACCEPTED'
              AND (c.sender_id=? OR c.receiver_id=?)
              AND u.id != ?
        """;

        try {
            Connection con = ConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, userId);
            ps.setLong(2, userId);
            ps.setLong(3, userId);
            return ps.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }
}
