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

/**
 * rest-контроллер для таблицы account
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UsersController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    /**
     * @return все записи в таблице account
     */
    @GetMapping(value="/admin/users/get")
    public ResponseEntity<List<User>> getAllUsersAdmin() {
        return userService.getAllAdmin();
    }

    /**
     * @param id id записи в таблице account
     * @return запись с соответсвующим pk в таблице account
     */
    @GetMapping(value="/admin/users/get/{id}")
    public ResponseEntity<User> getUserByIdAdmin(@PathVariable("id") long id) {
        return userService.getByIdAdmin(id);
    }

    /**
     * @param request реквест класса PostUserRequest
     * @return http-статус с кодом завершения операции
     */
    @PostMapping(value = "/admin/users/post",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> postUserAdmin(@RequestBody PostUserRequest request) {
       return ResponseEntity.ok(userService.postAdmin(request));
    }

    /**
     * @param id id записи в таблице account
     * @param request реквест класса PostUserRequest
     * @return http-статус с кодом завершения операции
     */
    @PutMapping(value = "/admin/users/put/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> updateUserByIdAdmin(@PathVariable("id") long id, @RequestBody PostUserRequest request) {
       return ResponseEntity.ok(userService.putAdmin(request, id));
    }

    /**
     * @param id id записи в таблице account
     * @return http-статус с кодом звершения операции
     */
    @DeleteMapping("/admin/users/delete/{id}")
    public ResponseEntity<UserResponse> deleteUserByIdAdmin(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.deleteByIdAdmin(id));
    }

    /**
     * @return все записи в таблице account за исключением пароля
     */
    @GetMapping(value = "/employee/users/get")
    public ResponseEntity<List<User>> getAllUsersEmployee() {
        return userService.getAllEmployee();
    }

    /**
     * @param id id записи в таблице account
     * @return соответсвующая запись в таблице account за исключением пароля
     */
    @GetMapping(value = "/employee/users/get/{id}")
    public ResponseEntity<User> getUserByIdEmployee(@PathVariable("id") long id) {
        return userService.getByIdEmployee(id);
    }

    /**
     * @param authHeader
     * @return
     */
    @GetMapping(value = "/user/me/info")
    public ResponseEntity<User> getUserByToken(@RequestHeader(value = "Authorization") String authHeader) {
        return userService.getByTokenUser(authHeader);
    }

}
