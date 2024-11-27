package mdeis.module1.blog.usecase.post;

import mdeis.module1.blog.app.post.api.UpdatePostApi;
import mdeis.module1.blog.data.PostRepository;
import mdeis.module1.blog.domain.*;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import mdeis.module1.blog.usecase.category.GetCategoryById;
import mdeis.module1.blog.usecase.tag.GetListTag;
import mdeis.module1.blog.usecase.user.GetUserById;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdatePost {

    private final PostRepository postRepository;
    private final GetListTag getListTag;
    private final GetCategoryById getCategoryById;
    private final GetUserById getUserById;

    public UpdatePost(PostRepository postRepository, GetListTag getListTag, GetCategoryById getCategoryById, GetUserById getUserById) {
        this.postRepository = postRepository;
        this.getListTag = getListTag;
        this.getCategoryById = getCategoryById;
        this.getUserById = getUserById;
    }

    public Post invoke(Post post, UpdatePostApi updatePostApi) {
        List<Tag> tagList = getListTag.invoke(updatePostApi.getTags());
        Category category = getCategoryById.invoke(updatePostApi.getCategoryId());
        User approvedBy = getUserById.invoke(updatePostApi.getApprovedBy());

        try {
            post.setTitle(updatePostApi.getTitle());
            post.setDescription(updatePostApi.getDescription());
            post.setContent(updatePostApi.getContent());
            post.setFrontpage(updatePostApi.getFrontpage());
            post.setStatus(updatePostApi.getStatus());
            post.setCategory(category);
            post.setApprovedBy(approvedBy);
            post.setTags(tagList);
            post = postRepository.save(post);
        } catch (Exception e) {
            throw new SaveEntityException("Error updating post: " + e.getMessage());
        }
        return post;
    }

}
