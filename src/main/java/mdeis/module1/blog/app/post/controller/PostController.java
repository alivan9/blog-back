package mdeis.module1.blog.app.post.controller;

import jakarta.validation.Valid;
import mdeis.module1.blog.app.post.api.*;
import mdeis.module1.blog.app.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PostApi> createPost(@RequestBody @Valid NewPostApi newPostApi) {
        return new ResponseEntity<>(postService.createPost(newPostApi), HttpStatus.OK);
    }

    @GetMapping(value = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PostApi> readPost(@PathVariable Integer postId) {
        return new ResponseEntity<>(postService.readPost(postId), HttpStatus.OK);
    }

    @PutMapping(value = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PostApi> updatePost(@PathVariable Integer postId, @RequestBody @Valid UpdatePostApi updatePostApi) {
        return new ResponseEntity<>(postService.updatePost(postId, updatePostApi), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>("Post deleted.", HttpStatus.OK);
    }

    @GetMapping(value = "filters", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<FilterPostResponseApi>> getPostByFiler(@RequestBody FilterPostRequestApi filterPostRequestApi) {
        return new ResponseEntity<>(postService.getPostByFilter(filterPostRequestApi), HttpStatus.OK);
    }
}
