package mdeis.module1.blog.usecase.comment;

import mdeis.module1.blog.app.comment.api.NewCommentApi;
import mdeis.module1.blog.data.CommentRepository;
import mdeis.module1.blog.domain.Comment;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import org.springframework.stereotype.Component;


@Component
public class UpdateComment {

    private final CommentRepository commentRepository;

    public UpdateComment(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment invoke(Comment comment, NewCommentApi newCommentApi) {
        try {
            comment.setDescription(newCommentApi.getDescription());
            comment = commentRepository.save(comment);
        } catch (Exception e) {
            throw new SaveEntityException("Error updating comment: " + e.getMessage());
        }
        return comment;
    }

}
