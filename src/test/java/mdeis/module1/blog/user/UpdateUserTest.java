package mdeis.module1.blog.user;

import mdeis.module1.blog.app.user.api.NewUserApi;
import mdeis.module1.blog.data.UserRepository;
import mdeis.module1.blog.domain.Role;
import mdeis.module1.blog.domain.User;
import mdeis.module1.blog.domain.UserStatus;
import mdeis.module1.blog.usecase.user.GetListRole;
import mdeis.module1.blog.usecase.user.UpdateUser;
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
public class UpdateUserTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private GetListRole getListRole;

    @InjectMocks
    private UpdateUser updateUser;

    @Test
    void testInvokeSuccess() {
        User user = new User();
        user.setName("John");
        user.setLastName("Doe");
        user.setUsername("johndoe");
        user.setPassword("oldpassword");
        user.setStatus(UserStatus.ACTIVE);

        NewUserApi newUserApi = new NewUserApi();
        newUserApi.setName("Jane");
        newUserApi.setLastName("Smith");
        newUserApi.setUsername("janesmith");
        newUserApi.setPassword("newpassword");
        newUserApi.setStatus(UserStatus.INACTIVE);
        newUserApi.setRoles(Collections.singletonList(1));

        Role role = new Role();
        role.setName("ROLE_ADMIN");

        when(getListRole.invoke(newUserApi.getRoles())).thenReturn(Collections.singletonList(role));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = updateUser.invoke(user, newUserApi);

        assertNotNull(result);
        assertEquals("Jane", result.getName());
        assertEquals("Smith", result.getLastName());
        assertEquals("janesmith", result.getUsername());
        assertEquals("newpassword", result.getPassword());
        assertEquals(UserStatus.INACTIVE, result.getStatus());
        assertEquals(1, result.getRoles().size());
        assertEquals("ROLE_ADMIN", result.getRoles().get(0).getName());
    }

    @Test
    void testInvokeThrowsSaveEntityException() {
        User user = new User();
        user.setName("John");
        user.setLastName("Doe");
        user.setUsername("johndoe");
        user.setPassword("oldpassword");
        user.setStatus(UserStatus.ACTIVE);

        NewUserApi newUserApi = new NewUserApi();
        newUserApi.setName("Jane");
        newUserApi.setLastName("Smith");
        newUserApi.setUsername("janesmith");
        newUserApi.setPassword("newpassword");
        newUserApi.setStatus(UserStatus.INACTIVE);
        newUserApi.setRoles(Collections.singletonList(1));

        when(getListRole.invoke(newUserApi.getRoles())).thenThrow(new RuntimeException("Roles not found"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            updateUser.invoke(user, newUserApi);
        });

        assertEquals("Roles not found", exception.getMessage());

        verify(userRepository, never()).save(any(User.class));
    }
}
