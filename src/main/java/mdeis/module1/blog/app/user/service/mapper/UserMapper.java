package mdeis.module1.blog.app.user.service.mapper;

import mdeis.module1.blog.app.user.api.UserApi;
import mdeis.module1.blog.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserApi map(User user) {
        UserApi userApi = new UserApi();
        userApi.setId(user.getId());
        userApi.setName(user.getName());
        userApi.setLastName(user.getLastName());
        userApi.setUsername(user.getUsername());
        userApi.setPassword(user.getPassword());
        userApi.setStatus(user.getStatus());
        userApi.setRoles(user.getRoles());
        userApi.setScores(user.getScores());
        userApi.setFollowing(user.getFollowing() != null ? user.getFollowing().stream().toList() : null);
        userApi.setFollowers(user.getFollowers() != null ? user.getFollowers().stream().toList() : null);
        return userApi;
    }
}
