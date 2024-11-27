package mdeis.module1.blog.usecase.post;

import org.springframework.stereotype.Component;

@Component
public class PostUseCase {
    public final CreatePost createPost;
    public final GetPostById getPostById;
    public final UpdatePost updatePost;
    public final DeletePost deletePost;
    public final GetPostByFilters getPostByFilters;

    public PostUseCase(CreatePost createPost, GetPostById getPostById, UpdatePost updatePost, DeletePost deletePost, GetPostByFilters getPostByFilters) {
        this.createPost = createPost;
        this.getPostById = getPostById;
        this.updatePost = updatePost;
        this.deletePost = deletePost;
        this.getPostByFilters = getPostByFilters;
    }
}
