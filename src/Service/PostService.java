package Service;

import Models.Post;
import Repository.PostRepository;

import java.util.List;

public class PostService {

    private final PostRepository repo = new PostRepository();

    public boolean createPost(long userId, String content) {
        return repo.create(userId, content);
    }

    public List<Post> getMyPosts(long userId) {
        return repo.getByUser(userId);
    }


    public boolean deletePost(long postId, long userId) {
        return repo.delete(postId, userId);
    }
}
