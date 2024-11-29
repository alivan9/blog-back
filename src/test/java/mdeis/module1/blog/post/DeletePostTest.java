package mdeis.module1.blog.post;

import mdeis.module1.blog.data.PostRepository;
import mdeis.module1.blog.domain.*;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import mdeis.module1.blog.usecase.post.DeletePost;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeletePostTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private DeletePost deletePost;


    @Test
    void testDeletePostSuccess() {
        Post post = new Post();
        post.setStatus(PostStatus.CREATED);

        Post deletedPost = new Post();
        deletedPost.setStatus(PostStatus.DELETED);

        when(postRepository.save(post)).thenReturn(deletedPost);

        Post result = deletePost.invoke(post);

        assertNotNull(result);
        assertEquals(PostStatus.DELETED, result.getStatus());

        verify(postRepository, times(1)).save(post);
    }

    @Test
    void testDeletePostThrowsSaveEntityException() {
        Post post = new Post();
        post.setStatus(PostStatus.CREATED);

        when(postRepository.save(post)).thenThrow(new RuntimeException("Database error"));

        SaveEntityException exception = assertThrows(SaveEntityException.class, () -> {
            deletePost.invoke(post);
        });

        assertEquals("Error deleting post: Database error", exception.getMessage());

        verify(postRepository, times(1)).save(post);
    }
}
