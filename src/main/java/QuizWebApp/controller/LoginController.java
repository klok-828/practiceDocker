package QuizWebApp.controller;

import QuizWebApp.domain.User;
import QuizWebApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    LoginController(UserService userService) { this.userService = userService; }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam String email,
                            @RequestParam String password,
                            HttpServletRequest request) {

        Optional<User> possibleUser = userService.validateUser(email, password);
        Optional<User> possibleAdmin = userService.validateAdmin(email, password);

        if(possibleAdmin.isPresent()) {
            HttpSession oldSession = request.getSession(false);

            if (oldSession != null) oldSession.invalidate();

            HttpSession newSession = request.getSession(true);

            newSession.setAttribute("user", possibleUser.get());

            return "redirect:/admin";
        } else if (possibleUser.isPresent()) {
            HttpSession oldSession = request.getSession(false);

            if (oldSession != null) oldSession.invalidate();

            HttpSession newSession = request.getSession(true);

            newSession.setAttribute("user", possibleUser.get());
            return "redirect:/home";
        } else
            return "redirect:/login";


    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession oldSession = request.getSession(false);
        // invalidate old session if it exists
        if(oldSession != null) oldSession.invalidate();
        return "login";
    }
}
