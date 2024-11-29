package mdeis.module1.blog.user;

import mdeis.module1.blog.app.user.api.NewUserApi;
import mdeis.module1.blog.data.UserRepository;
import mdeis.module1.blog.domain.Role;
import mdeis.module1.blog.domain.User;
import mdeis.module1.blog.domain.UserStatus;
import mdeis.module1.blog.usecase.user.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUserTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private GetListRole getListRole;

    @InjectMocks
    private CreateUser createUser;

    @Test
    void testInvokeSuccess() {
        NewUserApi newUserApi = new NewUserApi();
        newUserApi.setName("John");
        newUserApi.setLastName("Doe");
        newUserApi.setUsername("johndoe");
        newUserApi.setPassword("password123");
        newUserApi.setRoles(Collections.singletonList(1));

        Role role = new Role();
        role.setName("ROLE_USER");

        User savedUser = new User();
        savedUser.setName("John");
        savedUser.setLastName("Doe");
        savedUser.setUsername("johndoe");
        savedUser.setPassword("password123");
        savedUser.setStatus(UserStatus.ACTIVE);
        savedUser.setRoles(Collections.singletonList(role));

        when(getListRole.invoke(newUserApi.getRoles())).thenReturn(Collections.singletonList(role));
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = createUser.invoke(newUserApi);

        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals("Doe", result.getLastName());
        assertEquals("johndoe", result.getUsername());
        assertEquals(UserStatus.ACTIVE, result.getStatus());
        assertEquals(1, result.getRoles().size());
        assertEquals("ROLE_USER", result.getRoles().get(0).getName());
    }

    @Test
    void testInvokeThrowsSaveEntityException() {
        NewUserApi newUserApi = new NewUserApi();
        newUserApi.setName("John");
        newUserApi.setLastName("Doe");
        newUserApi.setUsername("johndoe");
        newUserApi.setPassword("password123");
        newUserApi.setRoles(Collections.singletonList(1));

        when(getListRole.invoke(newUserApi.getRoles())).thenThrow(new RuntimeException("Roles not found"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            createUser.invoke(newUserApi);
        });

        assertEquals("Roles not found", exception.getMessage());

        verify(userRepository, never()).save(any(User.class));
    }
}
