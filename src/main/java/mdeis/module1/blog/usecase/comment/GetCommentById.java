package mdeis.module1.blog.usecase.comment;

import jakarta.persistence.EntityNotFoundException;
import mdeis.module1.blog.data.CommentRepository;
import mdeis.module1.blog.domain.Comment;
import org.springframework.stereotype.Component;

@Component
public class GetCommentById {

    private final CommentRepository commentRepository;

    public GetCommentById(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment invoke(Integer commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException("Comment not found with id: " + commentId));
    }

}
