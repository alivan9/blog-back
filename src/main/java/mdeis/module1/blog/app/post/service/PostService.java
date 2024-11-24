package mdeis.module1.blog.app.post.service;

import mdeis.module1.blog.app.post.api.NewPostApi;
import mdeis.module1.blog.app.post.api.PostApi;
import mdeis.module1.blog.app.post.api.UpdatePostApi;

public interface PostService {

    PostApi createPost(NewPostApi newPostApi);

    PostApi readPost(Integer postId);

    PostApi updatePost(Integer postId, UpdatePostApi updatePostApi);

    void deletePost(Integer postId);
}
