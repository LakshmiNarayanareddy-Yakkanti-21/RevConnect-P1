package Models;

public class Comment {

    private long id;
    private long userId;
    private long postId;
    private String text;

    public Comment(long id, long userId, long postId, String text) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getPostId() {
        return postId;
    }

    public String getText() {
        return text;
    }
}
