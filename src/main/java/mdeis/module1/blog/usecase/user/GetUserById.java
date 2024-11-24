package mdeis.module1.blog.usecase.user;

import jakarta.persistence.EntityNotFoundException;
import mdeis.module1.blog.data.UserRepository;
import mdeis.module1.blog.domain.User;
import org.springframework.stereotype.Component;

@Component
public class GetUserById {

    private final UserRepository userRepository;

    public GetUserById(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User invoke(Integer userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found with id: " + userId));
    }

}
