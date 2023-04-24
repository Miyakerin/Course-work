package coursework.server.controllers;

import coursework.server.exceptions.BadRequestException;
import coursework.server.exceptions.NotFoundException;
import coursework.server.models.User;
import coursework.server.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController { //to-so sort, delete, manual insertion, searching loans
    @Autowired
    UsersRepository usersRepository;

    @GetMapping(value="/get")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(usersRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> user = usersRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            throw new NotFoundException();
        }
    }

    @PostMapping(value = "/post",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> postUser(@RequestBody User newUser) {
        User user = usersRepository.save(newUser);
        if (user == null) {
            throw new BadRequestException();
        } else {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/put/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User updUser) {
        Optional<User> curUser = usersRepository.findById(id);
        if (curUser.isPresent()) {
            updUser.setId(id);
            usersRepository.save(updUser);
            return new ResponseEntity<>(updUser, HttpStatus.ACCEPTED);
        } else {
            throw new NotFoundException();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUserById(@PathVariable("id") long id) {
        Optional<User> curUser = usersRepository.findById(id);
        if (curUser.isPresent()) {
            usersRepository.deleteById(id);
        } else {
            throw new NotFoundException();
        }
    }



}
