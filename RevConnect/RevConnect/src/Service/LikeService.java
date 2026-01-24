package Service;

import Repository.LikeRepository;

public class LikeService {

    private final LikeRepository repo = new LikeRepository();

    public void like(long userId, long postId) {
        boolean success = repo.likePost(userId, postId);
        if (success) {
            System.out.println("Post liked");
        } else {
            System.out.println("Already liked or error");
        }
    }

    public void unlike(long userId, long postId) {
        boolean success = repo.unlikePost(userId, postId);
        if (success) {
            System.out.println("Post unliked");
        } else {
            System.out.println("Like not found");
        }
    }
}
