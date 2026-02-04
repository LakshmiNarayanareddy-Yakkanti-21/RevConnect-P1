package Service;

import Repository.ConnectionRepository;

import java.sql.ResultSet;

public class ConnectionService {

    private final ConnectionRepository repo = new ConnectionRepository();

    public void sendRequest(long senderId, long receiverId) {
        repo.sendRequest(senderId, receiverId);
        System.out.println("Connection request sent");
    }

    public void viewPending(long userId) {
        try {
            ResultSet rs = repo.getPendingRequests(userId);

            if (rs == null || !rs.isBeforeFirst()) {
                System.out.println("No pending requests");
                return;
            }

            while (rs.next()) {
                System.out.println("Request ID: " + rs.getLong("id"));
                System.out.println("From: " + rs.getString("email"));
                System.out.println("----------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void accept(long requestId) {
        repo.updateStatus(requestId, "ACCEPTED");
        System.out.println("Request accepted");
    }

    public void reject(long requestId) {
        repo.updateStatus(requestId, "REJECTED");
        System.out.println("Request rejected");
    }

    public void viewConnections(long userId) {
        try {
            ResultSet rs = repo.getConnections(userId);

            if (rs == null || !rs.isBeforeFirst()) {
                System.out.println("No connections");
                return;
            }

            while (rs.next()) {
                System.out.println(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
