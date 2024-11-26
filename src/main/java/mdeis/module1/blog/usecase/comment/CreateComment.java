package mdeis.module1.blog.usecase.comment;

import mdeis.module1.blog.app.comment.api.NewCommentApi;
import mdeis.module1.blog.data.CommentRepository;
import mdeis.module1.blog.domain.Comment;
import mdeis.module1.blog.domain.Post;
import mdeis.module1.blog.domain.User;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import mdeis.module1.blog.usecase.post.GetPostById;
import mdeis.module1.blog.usecase.user.GetUserById;
import org.springframework.stereotype.Component;


@Component
public class CreateComment {

    private final CommentRepository commentRepository;
    private final GetPostById getPostById;
    private final GetUserById getUserById;

    public CreateComment(CommentRepository commentRepository, GetPostById getPostById, GetUserById getUserById) {
        this.commentRepository = commentRepository;
        this.getPostById = getPostById;
        this.getUserById = getUserById;
    }

    public Comment invoke(NewCommentApi newCommentApi) {
        Post post = getPostById.invoke(newCommentApi.getPostId());
        User user = getUserById.invoke(newCommentApi.getUserId());

        Comment comment = new Comment();
        try {
            comment.setDescription(newCommentApi.getDescription());
            comment.setPost(post);
            comment.setUser(user);
            comment = commentRepository.save(comment);
        } catch (Exception e) {
            throw new SaveEntityException("Error creating Comment: " + e.getMessage());
        }
        return comment;
    }

}
