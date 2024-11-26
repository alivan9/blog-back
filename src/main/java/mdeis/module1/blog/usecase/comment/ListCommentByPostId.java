package mdeis.module1.blog.usecase.comment;

import mdeis.module1.blog.data.CommentRepository;
import mdeis.module1.blog.domain.Comment;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListCommentByPostId {

    private final CommentRepository commentRepository;

    public ListCommentByPostId(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> invoke(Integer postId) {
        List<Comment> comments;
        try {
            comments = commentRepository.findByPostId(postId);
        } catch (Exception e) {
            throw new SaveEntityException("Error getting comments: " + e.getMessage());
        }
        return comments;
    }

}
