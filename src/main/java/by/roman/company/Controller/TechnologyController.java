package by.roman.company.Controller;

import by.roman.company.Entity.Technology;
import by.roman.company.Service.TechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/technology")
public class TechnologyController {

    private final TechnologyService technologyService;

    @GetMapping
    public String findAllVacancies(Model model) {
        List<Technology> technologies = technologyService.findAllTechnologies();
        model.addAttribute("technologies", technologies);
        return "technology";
    }

    @GetMapping("/new")
    public String addTechnology(Model model) {
        model.addAttribute("technology", new Technology());
        return "new_technology";
    }

    @PostMapping
    public String saveTechnology(Technology technology) {
        technologyService.saveTechnology(technology);
        return "redirect:/technology";
    }

    @GetMapping("/delete/{id}")
    public String deleteTechnology(@PathVariable(value = "id") Integer id) {
        String page;
        try {
            technologyService.deleteTechnology(Optional.ofNullable(id).orElse(null));
            page = "redirect:/technology";
        } catch (Exception e) {
            e.printStackTrace();
            page = "technologyError";
        }

        return page;
    }

    @GetMapping("/update/{id}")
    public String updateTechnology(@PathVariable(value = "id") Integer id, Model model) {
        Technology technology = technologyService.findTechnologyById(id);
        model.addAttribute("technology", technology);
        return "update_technology";
    }
}
