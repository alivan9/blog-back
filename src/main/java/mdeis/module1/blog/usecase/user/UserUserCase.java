package mdeis.module1.blog.usecase.user;

import org.springframework.stereotype.Component;

@Component
public class UserUserCase {
    public final CreateUser createUser;
    public final GetUserById getUserById;
    public final UpdateUser updateUser;
    public final DeleteUser deleteUser;

    public UserUserCase(CreateUser createUser, GetUserById getUserById, UpdateUser updateUser, DeleteUser deleteUser) {
        this.createUser = createUser;
        this.getUserById = getUserById;
        this.updateUser = updateUser;
        this.deleteUser = deleteUser;
    }
}
