package coursework.server.Service;

import coursework.server.Request.PostUserRequest;
import coursework.server.Response.UserResponse;
import coursework.server.exceptions.UserExistException;
import coursework.server.models.Role;
import coursework.server.models.User;
import coursework.server.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public ResponseEntity<List<User>> getAllAdmin() {
        return new ResponseEntity<>(usersRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<User> getByIdAdmin(long id) {
        Optional<User> user = usersRepository.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    public UserResponse postAdmin(PostUserRequest request) {
        if (usersRepository.findByEmail(request.getEmail()).isPresent()) {
            return UserResponse.builder().success(false).build();
        }
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .age(request.getAge())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole().equals("ADMIN") ? Role.ADMIN: request.getRole().equals("EMPLOYEE") ? Role.EMPLOYEE:Role.USER)
                .build();
        usersRepository.save(user);
        return UserResponse.builder().success(true).build();
    }

    public UserResponse putAdmin(PostUserRequest request, long id) {
        if (usersRepository.findById(id).isEmpty()) {
            return UserResponse.builder().success(false).build();
        }
        if (!usersRepository.findById(id).get().getEmail().equals(request.getEmail()) &&
            usersRepository.findByEmail(request.getEmail()).isPresent()) {
            return UserResponse.builder().success(false).build();
        }
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .age(request.getAge())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole().equals("ADMIN") ? Role.ADMIN: request.getRole().equals("EMPLOYEE") ? Role.EMPLOYEE:Role.USER)
                .id(id)
                .build();
        usersRepository.save(user);
        return UserResponse.builder().success(true).build();
    }

    public UserResponse deleteByIdAdmin(long id) {
        if (usersRepository.findById(id).isEmpty()) {
            return UserResponse.builder().success(false).build();
        }
        usersRepository.deleteById(id);
        return UserResponse.builder().success(true).build();
    }

    public ResponseEntity<List<User>> getAllEmployee() {
        List<User> userList = usersRepository.findAll();
        for (int i = 0; i < userList.size(); i++) {
            userList.get(i).setPassword("");
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    public ResponseEntity<User> getByIdEmployee(long id) {
        Optional<User> user = usersRepository.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.get().setPassword("");
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    public ResponseEntity<User> getByTokenUser(String jwtToken) {
        String userEmail = jwtService.extractUserEmail(jwtToken);
        System.out.println(jwtToken);
        System.out.println(userEmail);
        if (usersRepository.findByEmail(userEmail).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usersRepository.findByEmail(userEmail).get(), HttpStatus.OK);

    }
}
