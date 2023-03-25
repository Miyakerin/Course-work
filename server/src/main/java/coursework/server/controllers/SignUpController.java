package coursework.server.controllers;

import coursework.server.models.User;
import coursework.server.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp_page";
    }

    @PostMapping("/signUp") //to-do: data validation
    public String signUpUser(User user) {

        usersRepository.save(user);
        return "redirect:/signUp";
    }

}
