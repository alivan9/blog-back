package mdeis.module1.blog.app.post.service;

import mdeis.module1.blog.app.post.api.*;

import java.util.List;

public interface PostService {

    PostApi createPost(NewPostApi newPostApi);

    PostApi readPost(Integer postId);

    PostApi updatePost(Integer postId, UpdatePostApi updatePostApi);

    void deletePost(Integer postId);

    List<FilterPostResponseApi> getPostByFilter(FilterPostRequestApi filterPostRequestApi);
}
