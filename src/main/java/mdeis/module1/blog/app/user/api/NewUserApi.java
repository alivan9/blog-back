package mdeis.module1.blog.app.user.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mdeis.module1.blog.domain.UserStatus;

import java.util.List;

@Data
public class NewUserApi {

    @NotBlank
    String name;
    @NotBlank
    String lastName;
    @NotBlank
    String username;
    @NotBlank
    String password;
    @NotNull
    UserStatus status;
    List<Integer> roles;
}
