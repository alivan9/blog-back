package mdeis.module1.blog.app.comment.controller;

import jakarta.validation.Valid;
import mdeis.module1.blog.app.comment.api.CommentApi;
import mdeis.module1.blog.app.comment.api.NewCommentApi;
import mdeis.module1.blog.app.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/comments", produces = MediaType.APPLICATION_JSON_VALUE)
public class  CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommentApi> createComment(@RequestBody @Valid NewCommentApi newCommentApi) {
        return new ResponseEntity<>(commentService.createComment(newCommentApi), HttpStatus.OK);
    }

    @GetMapping(value = "/post/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CommentApi>> readCategory(@PathVariable Integer postId) {
        return new ResponseEntity<>(commentService.listCommentByPostId(postId), HttpStatus.OK);
    }

    @PutMapping(value = "/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommentApi> updateComment(@PathVariable Integer commentId, @RequestBody @Valid NewCommentApi newCommentApi) {
        return new ResponseEntity<>(commentService.updateComment(commentId, newCommentApi), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Comment deleted.", HttpStatus.OK);
    }
}
