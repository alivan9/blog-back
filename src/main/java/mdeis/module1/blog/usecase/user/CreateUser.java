package mdeis.module1.blog.usecase.user;

import mdeis.module1.blog.app.user.api.NewUserApi;
import mdeis.module1.blog.data.UserRepository;
import mdeis.module1.blog.domain.Role;
import mdeis.module1.blog.domain.User;
import mdeis.module1.blog.domain.UserStatus;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateUser {

    private final UserRepository userRepository;
    private final GetListRole getListRole;

    public CreateUser(UserRepository userRepository, GetListRole getListRole) {
        this.userRepository = userRepository;
        this.getListRole = getListRole;
    }

    public User invoke(NewUserApi newUserApi) {
        List<Role> rolesList = getListRole.invoke(newUserApi.getRoles());
        User user = new User();
        try {
            user.setName(newUserApi.getName());
            user.setLastName(newUserApi.getLastName());
            user.setUsername(newUserApi.getUsername());
            user.setPassword(newUserApi.getPassword());
            user.setStatus(UserStatus.ACTIVE);
            user.setRoles(rolesList);
            user = userRepository.save(user);
        } catch (Exception e) {
            throw new SaveEntityException("Error creating user: " + e.getMessage());
        }

        return user;
    }

}
