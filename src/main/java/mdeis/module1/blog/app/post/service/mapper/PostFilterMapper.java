package mdeis.module1.blog.app.post.service.mapper;

import mdeis.module1.blog.app.post.api.FilterPostResponseApi;
import mdeis.module1.blog.domain.Post;
import org.springframework.stereotype.Component;

@Component
public class PostFilterMapper {

    public FilterPostResponseApi map(Post post) {
        FilterPostResponseApi postApi = new FilterPostResponseApi();
        postApi.setId(post.getId());
        postApi.setTitle(post.getTitle());
        postApi.setDescription(post.getDescription());
        postApi.setFrontpage(post.getFrontpage());
        postApi.setCategory(post.getCategory().getName());
        postApi.setCreatedAt(post.getCreatedAt());
        return postApi;
    }
}
