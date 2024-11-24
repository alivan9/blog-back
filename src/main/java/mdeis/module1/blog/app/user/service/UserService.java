package mdeis.module1.blog.app.user.service;

import mdeis.module1.blog.app.user.api.NewUserApi;
import mdeis.module1.blog.app.user.api.UserApi;

public interface UserService {

    UserApi createUser(NewUserApi newUserApi);

    UserApi readUser(Integer userId);

    UserApi updateUser(Integer userId, NewUserApi newUserApi);

    void deleteUser(Integer userId);
}
