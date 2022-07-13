package by.roman.company.Controller;

import by.roman.company.Entity.User;
import by.roman.company.Enum.Role;
import by.roman.company.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String newUser() {
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Model model) {
        User userFromBd = userService.findByUsername(user.getUsername());
        if (userFromBd != null) {
            model.addAttribute("message", "User exist!");
            return "registration";
        } else {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userService.saveUser(user);
        }
        return "redirect:/login";
    }
}
