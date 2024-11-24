package mdeis.module1.blog.usecase.post;

import jakarta.persistence.EntityNotFoundException;
import mdeis.module1.blog.data.PostRepository;
import mdeis.module1.blog.domain.Post;
import org.springframework.stereotype.Component;

@Component
public class GetPostById {

    private final PostRepository postRepository;

    public GetPostById(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post invoke(Integer postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("Post not found with id: " + postId));
    }

}
