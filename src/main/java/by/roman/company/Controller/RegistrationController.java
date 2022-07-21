package by.roman.company.Controller;

import by.roman.company.Entity.User;
import by.roman.company.Enum.Role;
import by.roman.company.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;


    @GetMapping
    public String newUser(User user, Model model) {
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Model model) {
        if (userService.findByUsername(user.getUsername())!= null) {
            model.addAttribute("error", "error");
            return "registration";
        } else {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);

        }
        return "redirect:/login";
    }
}
