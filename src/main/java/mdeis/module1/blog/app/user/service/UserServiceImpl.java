package mdeis.module1.blog.app.user.service;

import mdeis.module1.blog.app.user.api.NewUserApi;
import mdeis.module1.blog.app.user.api.UserApi;
import mdeis.module1.blog.app.user.service.mapper.UserMapper;
import mdeis.module1.blog.domain.User;
import mdeis.module1.blog.usecase.user.UserUserCase;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserUserCase userUserCase;
    private final UserMapper userMapper;

    public UserServiceImpl(UserUserCase userUserCase, UserMapper userMapper) {
        this.userUserCase = userUserCase;
        this.userMapper = userMapper;
    }

    @Override
    public UserApi createUser(NewUserApi newUserApi) {
        return userMapper.map(userUserCase.createUser.invoke(newUserApi));
    }

    @Override
    public UserApi readUser(Integer userId) {
        return userMapper.map(userUserCase.getUserById.invoke(userId));
    }

    @Override
    public UserApi updateUser(Integer userId, NewUserApi newUserApi) {
        User user = userUserCase.getUserById.invoke(userId);
        return userMapper.map(userUserCase.updateUser.invoke(user, newUserApi));
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userUserCase.getUserById.invoke(userId);
        userUserCase.deleteUser.invoke(user);
    }


}
