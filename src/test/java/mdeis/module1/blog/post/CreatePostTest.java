package mdeis.module1.blog.post;

import mdeis.module1.blog.app.post.api.NewPostApi;
import mdeis.module1.blog.data.PostRepository;
import mdeis.module1.blog.domain.*;
import mdeis.module1.blog.usecase.category.GetCategoryById;
import mdeis.module1.blog.usecase.post.CreatePost;
import mdeis.module1.blog.usecase.tag.GetListTag;
import mdeis.module1.blog.usecase.user.GetUserById;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreatePostTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private GetCategoryById getCategoryById;

    @Mock
    private GetListTag getListTag;

    @Mock
    private GetUserById getUserById;

    @InjectMocks
    private CreatePost createPost;

    @Test
    void testCreatePostSuccess() {
        NewPostApi newPostApi = new NewPostApi();
        newPostApi.setTitle("Test Title");
        newPostApi.setDescription("Test Description");
        newPostApi.setContent("Test Content");
        newPostApi.setFrontpage("asdasd");
        newPostApi.setCategoryId(1);
        newPostApi.setUserId(2);
        newPostApi.setTags(Collections.singletonList(1));

        Category category = new Category();
        category.setId(1);

        User user = new User();
        user.setId(2);

        Tag tag = new Tag();
        tag.setName("TestTag");

        Post post = new Post();
        post.setTitle("Test Title");

        when(getCategoryById.invoke(newPostApi.getCategoryId())).thenReturn(category);
        when(getUserById.invoke(newPostApi.getUserId())).thenReturn(user);
        when(getListTag.invoke(newPostApi.getTags())).thenReturn(Collections.singletonList(tag));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post result = createPost.invoke(newPostApi);

        assertNotNull(result);
        assertEquals("Test Title", result.getTitle());

        verify(getCategoryById, times(1)).invoke(newPostApi.getCategoryId());
        verify(getUserById, times(1)).invoke(newPostApi.getUserId());
        verify(getListTag, times(1)).invoke(newPostApi.getTags());
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    void testCreatePostThrowsSaveEntityException() {
        NewPostApi newPostApi = new NewPostApi();
        newPostApi.setCategoryId(1);

        when(getCategoryById.invoke(newPostApi.getCategoryId())).thenThrow(new RuntimeException("Category not found"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            createPost.invoke(newPostApi);
        });

        assertEquals("Category not found", exception.getMessage());

        verify(getUserById, never()).invoke(anyInt());
        verify(getListTag, never()).invoke(anyList());
        verify(postRepository, never()).save(any(Post.class));
    }
}
