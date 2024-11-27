package mdeis.module1.blog.usecase.post;

import jakarta.persistence.EntityNotFoundException;
import mdeis.module1.blog.app.post.api.FilterPostRequestApi;
import mdeis.module1.blog.data.PostRepository;
import mdeis.module1.blog.domain.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPostByFilters {

    private final PostRepository postRepository;

    public GetPostByFilters(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> invoke(FilterPostRequestApi filterPostRequestApi) {
        List<Post> posts = postRepository.findByCategoryAndList(filterPostRequestApi.getCategories(), filterPostRequestApi.getTags());
        if (!posts.isEmpty()) {
            return posts;
        } else {
            throw new EntityNotFoundException("Post not found with filters");
        }
    }

}
