package mdeis.module1.blog.app.user.controller;

import jakarta.validation.Valid;
import mdeis.module1.blog.app.user.api.NewUserApi;
import mdeis.module1.blog.app.user.api.UserApi;
import mdeis.module1.blog.app.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserApi> createUser(@RequestBody @Valid NewUserApi newUserApi) {
        return new ResponseEntity<>(userService.createUser(newUserApi), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserApi> readUser(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.readUser(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserApi> updateUser(@PathVariable Integer userId,@RequestBody @Valid NewUserApi newUserApi) {
        return new ResponseEntity<>(userService.updateUser(userId, newUserApi), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted.", HttpStatus.OK);
    }
}
