package mdeis.module1.blog.usecase.post;

import mdeis.module1.blog.app.post.api.NewPostApi;
import mdeis.module1.blog.data.PostRepository;
import mdeis.module1.blog.domain.*;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import mdeis.module1.blog.usecase.category.GetCategoryById;
import mdeis.module1.blog.usecase.tag.GetListTag;
import mdeis.module1.blog.usecase.user.GetUserById;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreatePost {

    private final PostRepository postRepository;
    private final GetCategoryById getCategoryById;
    private final GetListTag getListTag;
    private final GetUserById getUserById;

    public CreatePost(PostRepository postRepository, GetCategoryById getCategoryById, GetListTag getListTag, GetUserById getUserById) {
        this.postRepository = postRepository;
        this.getCategoryById = getCategoryById;
        this.getListTag = getListTag;
        this.getUserById = getUserById;
    }

    public Post invoke(NewPostApi newPostApi) {
        Category category = getCategoryById.invoke(newPostApi.getCategoryId());
        User user = getUserById.invoke(newPostApi.getUserId());
        List<Tag> tagList = getListTag.invoke(newPostApi.getTags());
        Post post = new Post();
        try {
            post.setTitle(newPostApi.getTitle());
            post.setDescription(newPostApi.getDescription());
            post.setStatus(PostStatus.CREATED);
            post.setCategory(category);
            post.setUser(user);
            post.setTags(tagList);
            post = postRepository.save(post);
        } catch (Exception e) {
            throw new SaveEntityException("Error creating Post: " + e.getMessage());
        }

        return post;
    }

}
