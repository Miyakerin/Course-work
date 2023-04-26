package coursework.server.controllers;

import coursework.server.Request.PostUserRequest;
import coursework.server.Response.UserResponse;
import coursework.server.Service.AuthenticationService;
import coursework.server.Service.UserService;
import coursework.server.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UsersController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping(value="/admin/users/get")
    public ResponseEntity<List<User>> getAllUsersAdmin() {
        return userService.getAllAdmin();
    }

    @GetMapping(value="/admin/users/get/{id}")
    public ResponseEntity<User> getUserByIdAdmin(@PathVariable("id") long id) {
        return userService.getByIdAdmin(id);
    }

    @PostMapping(value = "/admin/users/post",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> postUserAdmin(@RequestBody PostUserRequest request) {
       return ResponseEntity.ok(userService.postAdmin(request));
    }

    @PutMapping(value = "/admin/users/put/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> updateUserByIdAdmin(@PathVariable("id") long id, @RequestBody PostUserRequest request) {
       return ResponseEntity.ok(userService.putAdmin(request, id));
    }

    @DeleteMapping("/admin/users/delete/{id}")
    public ResponseEntity<UserResponse> deleteUserByIdAdmin(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.deleteByIdAdmin(id));
    }

    @GetMapping(value = "/employee/users/get")
    public ResponseEntity<List<User>> getAllUsersEmployee() {
        return userService.getAllEmployee();
    }

    @GetMapping(value = "/employee/users/get/{id}")
    public ResponseEntity<User> getUserByIdEmployee(@PathVariable("id") long id) {
        return userService.getByIdEmployee(id);
    }

    @GetMapping(value = "/user/me/info")
    public ResponseEntity<User> getUserByToken(@RequestHeader(value = "Authorization") String authHeader) {
        return userService.getByTokenUser(authHeader);
    }

}
