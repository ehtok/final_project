package by.roman.company.Controller;

import by.roman.company.DTO.CompanyDTO;
import by.roman.company.DTO.VacancyDTO;
import by.roman.company.Enum.*;
import by.roman.company.Service.CompanyService;
import by.roman.company.Service.TechnologyService;
import by.roman.company.Service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vacancy")
public class VacancyController {
    private final VacancyService vacancyService;
    private final CompanyService companyService;
    private final TechnologyService technologyService;


    @GetMapping
    public String findAllCourse(Model model,
                                @RequestParam(value = "page", required = false, defaultValue = "1")
                                int currentPage,
                                @RequestParam(value = "sortField", required = false, defaultValue = "name")
                                String field,
                                @RequestParam(value = "sortDir", required = false, defaultValue = "ASC")
                                String sortDir,
                                @RequestParam(value = "size", required = false, defaultValue = "5")
                                int size) {
        Page<VacancyDTO> page = vacancyService.findAllVacancyWithSort(field, sortDir, currentPage, size);
        int totalPages = page.getTotalPages();
        long totalHorses = page.getTotalElements();
        List<VacancyDTO> vacancyList = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPages);
        model.addAttribute("totalHorses", totalHorses);
        model.addAttribute("sortField", field);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", Sort.Direction.ASC.name().equals(sortDir) ? "DESC" : "ASC");
        model.addAttribute("vacancies", vacancyList);
        return "vacancy";
    }

    @GetMapping("/new/{id}")
    public String addVacancy(Model model, @PathVariable(value = "id") Integer id) {
        CompanyDTO company = companyService.findCompanyDTOById(id);
        model.addAttribute("vacancy", new VacancyDTO());
        model.addAttribute("companyId", company);
        model.addAttribute("location", LocationEnum.values());
        model.addAttribute("status", StatusEnum.values());
        model.addAttribute("workingTime", WorkingTimeEnum.values());
        model.addAttribute("professionLevel", ProfLevelEnum.values());
        model.addAttribute("englishLevel", EnglishLevelEnum.values());
        model.addAttribute("tech", technologyService.findAllTechnologies());
        return "new_vacancy";
    }


    @GetMapping(value = "/search")
    public String searchCompanyByName(Model model) {
        model.addAttribute("vacancy", new VacancyDTO());
        return "search_vacancy";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchVacancy(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        List<VacancyDTO> vacancyList = vacancyService.findVacanciesByName(name);
        model.addAttribute("vacancyList", vacancyList);
        return "search_vacancy";
    }

    @PostMapping
    public String saveVacancy(VacancyDTO vacancy) {
        vacancyService.saveVacancy(vacancy);
        return "redirect:/vacancy";
    }

    @GetMapping("/delete/{id}")
    public String deleteVacancy(@PathVariable(value = "id") Integer id) {
        vacancyService.deleteVacancy(id);
        return "redirect:/vacancy";
    }

    @GetMapping("/update/{id}")
    public String updateVacancy(@PathVariable(value = "id") Integer id, Model model) {
        VacancyDTO vacancy = vacancyService.findVacancyById(id);
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("status", StatusEnum.values());
        model.addAttribute("workingTime", WorkingTimeEnum.values());
        model.addAttribute("location", LocationEnum.values());
        model.addAttribute("professionLevel", ProfLevelEnum.values());
        model.addAttribute("english", EnglishLevelEnum.values());
        return "update_vacancy";
    }

    @GetMapping("/info/{id}")
    public String infoVacancy(@PathVariable(value = "id") Integer id, Model model) {
        VacancyDTO vacancy = vacancyService.findVacancyById(id);
        model.addAttribute("vacancy", vacancy);
        return "info_vacancy";
    }
}
