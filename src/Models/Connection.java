package Models;

public class Connection {
    private long id;
    private long senderId;
    private long receiverId;
    private String status; // PENDING, ACCEPTED, REJECTED

    public Connection(long id, long senderId, long receiverId, String status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public long getSenderId() {
        return senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public String getStatus() {
        return status;
    }
}
