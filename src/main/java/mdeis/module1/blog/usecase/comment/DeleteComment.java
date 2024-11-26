package mdeis.module1.blog.usecase.comment;

import mdeis.module1.blog.data.CommentRepository;
import mdeis.module1.blog.domain.Comment;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import org.springframework.stereotype.Component;

@Component
public class DeleteComment {

    private final CommentRepository commentRepository;

    public DeleteComment(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void invoke(Comment comment) {
        try {
            commentRepository.delete(comment);
        } catch (Exception e) {
            throw new SaveEntityException("Error deleting comment: " + e.getMessage());
        }
    }

}
