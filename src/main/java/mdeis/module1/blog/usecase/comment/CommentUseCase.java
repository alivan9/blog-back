package mdeis.module1.blog.usecase.comment;

import org.springframework.stereotype.Component;

@Component
public class CommentUseCase {
    public final CreateComment createComment;
    public final ListCommentByPostId listCommentByPostId;
    public final UpdateComment updateComment;
    public final GetCommentById getCommentById;
    public final DeleteComment deleteComment;

    public CommentUseCase(CreateComment createComment, ListCommentByPostId listCommentByPostId, UpdateComment updateComment, GetCommentById getCommentById, DeleteComment deleteComment) {
        this.createComment = createComment;
        this.listCommentByPostId = listCommentByPostId;
        this.updateComment = updateComment;
        this.getCommentById = getCommentById;
        this.deleteComment = deleteComment;
    }
}
