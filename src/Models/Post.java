package Models;

public class Post {

    private long id;
    private long userId;
    private String content;

    public Post(long id, long userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Post ID: " + id +
                ", User ID: " + userId +
                ", Content: " + content;
    }
}
