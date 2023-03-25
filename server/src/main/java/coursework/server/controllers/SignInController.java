package coursework.server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignInController {
    @PostMapping("/signIn")
    public String signInUser(){
        return "redirect:/signIn";
    }
    @GetMapping("/signIn") //to-do data validation
    public String getSignInPage(){
        return "signIn_page";
    }
}
