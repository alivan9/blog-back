package mdeis.module1.blog.app.post.service;

import mdeis.module1.blog.app.post.api.NewPostApi;
import mdeis.module1.blog.app.post.api.PostApi;
import mdeis.module1.blog.app.post.api.UpdatePostApi;
import mdeis.module1.blog.app.post.service.mapper.PostMapper;
import mdeis.module1.blog.domain.Post;
import mdeis.module1.blog.domain.User;
import mdeis.module1.blog.usecase.post.PostUseCase;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final PostUseCase postUseCase;
    private final PostMapper postMapper;

    public PostServiceImpl(PostUseCase postUseCase, PostMapper postMapper) {
        this.postUseCase = postUseCase;
        this.postMapper = postMapper;
    }

    @Override
    public PostApi createPost(NewPostApi newPostApi) {
        return postMapper.map(postUseCase.createPost.invoke(newPostApi));
    }

    @Override
    public PostApi readPost(Integer postId) {
        return postMapper.map(postUseCase.getPostById.invoke(postId));
    }

    @Override
    public PostApi updatePost(Integer postId, UpdatePostApi updatePostApi) {
        Post post = postUseCase.getPostById.invoke(postId);
        return postMapper.map(postUseCase.updatePost.invoke(post, updatePostApi));
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postUseCase.getPostById.invoke(postId);
        postUseCase.deletePost.invoke(post);
    }


}
