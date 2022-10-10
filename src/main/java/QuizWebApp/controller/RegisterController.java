package QuizWebApp.controller;

import QuizWebApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import QuizWebApp.service.UserService;

import java.util.List;

@Controller
public class RegisterController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) { this.userService = userService; }


    @GetMapping("/register")
    public String qw() {
        return "register";
    }

    @PostMapping("/api/register")
    @ResponseBody
    public String createNewUser(@RequestBody User user) {
        userService.createNewUser(user);
        return "User inserted";
    }

    @PostMapping("/register")
    public String  submitNewUser(@RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 @RequestParam String phone,
                                 @RequestParam String address,
                                 Model model) {
        User user = new User(firstName, lastName, email, password, phone, address, true, false);


        userService.createNewUser(user);

        return "redirect:/login";
    }
}
