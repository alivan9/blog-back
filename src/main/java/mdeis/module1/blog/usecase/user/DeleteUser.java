package mdeis.module1.blog.usecase.user;

import mdeis.module1.blog.data.UserRepository;
import mdeis.module1.blog.domain.User;
import mdeis.module1.blog.domain.UserStatus;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import org.springframework.stereotype.Component;

@Component
public class DeleteUser {

    private final UserRepository userRepository;

    public DeleteUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User invoke(User user) {
        try {
            user.setStatus(UserStatus.DELETED);
            user = userRepository.save(user);
        } catch (Exception e) {
            throw new SaveEntityException("Error deleting user: " + e.getMessage());
        }
        return user;
    }

}
