package by.roman.company.Controller;

import by.roman.company.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {


    @GetMapping
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }

}
