package mdeis.module1.blog.app.comment.service.mapper;

import mdeis.module1.blog.app.comment.api.CommentApi;
import mdeis.module1.blog.domain.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentApi map(Comment comment) {
        CommentApi commentApi = new CommentApi();
        commentApi.setId(comment.getId());
        commentApi.setDescription(comment.getDescription());
        commentApi.setCreatedAt(comment.getCreatedAt());
        commentApi.setUpdateAt(comment.getUpdatedAt());
        return commentApi;
    }
}
