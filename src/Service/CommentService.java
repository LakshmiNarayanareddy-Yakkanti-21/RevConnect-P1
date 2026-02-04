package Service;

import Repository.CommentRepository;

import java.sql.ResultSet;

public class CommentService {

    private final CommentRepository repo = new CommentRepository();

    public void addComment(long userId, long postId, String text) {
        repo.addComment(userId, postId, text);
        System.out.println("Comment added");
    }

    public void viewComments(long postId) {
        ResultSet rs = repo.getComments(postId);

        try {
            if (rs == null || !rs.isBeforeFirst()) {
                System.out.println("No comments found");
                return;
            }

            while (rs.next()) {
                System.out.println("----------------");
                System.out.println("Comment ID: " + rs.getLong("id"));
                System.out.println("By: " + rs.getString("email"));
                System.out.println(rs.getString("comment"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteComment(long commentId, long userId) {
        boolean success = repo.deleteComment(commentId, userId);
        System.out.println(success ? "Comment deleted" : "Cannot delete comment");
    }
}
