package mdeis.module1.blog.app.comment.service;

import mdeis.module1.blog.app.comment.api.CommentApi;
import mdeis.module1.blog.app.comment.api.NewCommentApi;

import java.util.List;

public interface CommentService {

    CommentApi createComment(NewCommentApi newCommentApi);

    List<CommentApi> listCommentByPostId(Integer postId);

    CommentApi updateComment(Integer commentId, NewCommentApi newCommentApi);

    void deleteComment(Integer commentId);
}
