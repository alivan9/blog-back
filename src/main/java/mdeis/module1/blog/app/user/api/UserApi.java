package mdeis.module1.blog.app.user.api;

import lombok.Data;
import mdeis.module1.blog.domain.Follows;
import mdeis.module1.blog.domain.Role;
import mdeis.module1.blog.domain.Score;
import mdeis.module1.blog.domain.UserStatus;

import java.util.List;

@Data
public class UserApi {

    Integer id;
    String name;
    String lastName;
    String username;
    String password;
    UserStatus status;
    List<Role> roles;
    List<Score> scores;
    List<Follows> following;
    List<Follows> followers;
}
