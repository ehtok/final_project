package by.roman.company.Controller;

import by.roman.company.Entity.User;
import by.roman.company.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    @GetMapping
    public String user(Model model) {
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping
    public String saveCompany(User user) {
        userService.saveUser(user);
        return "redirect:/user";
    }

    @GetMapping("/update")
    public String updateCompany(Model model) {
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "update_user";
    }


}
