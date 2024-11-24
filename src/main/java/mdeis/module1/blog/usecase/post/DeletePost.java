package mdeis.module1.blog.usecase.post;

import mdeis.module1.blog.data.PostRepository;
import mdeis.module1.blog.domain.Post;
import mdeis.module1.blog.domain.PostStatus;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import org.springframework.stereotype.Component;

@Component
public class DeletePost {

    private final PostRepository postRepository;

    public DeletePost(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post invoke(Post post) {
        try {
            post.setStatus(PostStatus.DELETED);
            post = postRepository.save(post);
        } catch (Exception e) {
            throw new SaveEntityException("Error deleting post: " + e.getMessage());
        }
        return post;
    }

}
