package mdeis.module1.blog.usecase.post;

import org.springframework.stereotype.Component;

@Component
public class PostUseCase {
    public final CreatePost createPost;
    public final GetPostById getPostById;
    public final UpdatePost updatePost;
    public final DeletePost deletePost;

    public PostUseCase(CreatePost createPost, GetPostById getPostById, UpdatePost updatePost, DeletePost deletePost) {
        this.createPost = createPost;
        this.getPostById = getPostById;
        this.updatePost = updatePost;
        this.deletePost = deletePost;
    }
}
