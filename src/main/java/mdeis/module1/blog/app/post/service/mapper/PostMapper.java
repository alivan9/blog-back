package mdeis.module1.blog.app.post.service.mapper;

import mdeis.module1.blog.app.post.api.PostApi;
import mdeis.module1.blog.domain.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostApi map(Post post) {
        PostApi postApi = new PostApi();
        postApi.setId(post.getId());
        postApi.setTitle(post.getTitle());
        postApi.setDescription(post.getDescription());
        postApi.setCategory(post.getCategory());
        postApi.setApprovedBy(post.getApprovedBy());
        postApi.setUser(post.getUser());
        postApi.setTags(post.getTags());
        postApi.setScores(post.getScores());
        return postApi;
    }
}
