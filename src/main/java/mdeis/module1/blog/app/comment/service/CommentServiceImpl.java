package mdeis.module1.blog.app.comment.service;

import mdeis.module1.blog.app.comment.api.CommentApi;
import mdeis.module1.blog.app.comment.api.NewCommentApi;
import mdeis.module1.blog.app.comment.service.mapper.CommentMapper;
import mdeis.module1.blog.domain.Comment;
import mdeis.module1.blog.usecase.comment.CommentUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentUseCase commentUseCase;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentUseCase commentUseCase, CommentMapper commentMapper) {
        this.commentUseCase = commentUseCase;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentApi createComment(NewCommentApi newCommentApi) {
        return commentMapper.map(commentUseCase.createComment.invoke(newCommentApi));
    }

    @Override
    public List<CommentApi> listCommentByPostId(Integer postId) {
        List<Comment> comments = commentUseCase.listCommentByPostId.invoke(postId);
        return comments.stream().map(commentMapper::map).toList();
    }

    @Override
    public CommentApi updateComment(Integer commentId, NewCommentApi newCommentApi) {
        Comment comment = commentUseCase.getCommentById.invoke(commentId);
        return commentMapper.map(commentUseCase.updateComment.invoke(comment, newCommentApi));
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentUseCase.getCommentById.invoke(commentId);
        commentUseCase.deleteComment.invoke(comment);
    }


}
