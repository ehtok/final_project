package by.roman.company.Controller;

import by.roman.company.DTO.CourseDTO;
import by.roman.company.Entity.Company;
import by.roman.company.Entity.Course;
import by.roman.company.Enum.LocationEnum;
import by.roman.company.Service.CompanyService;
import by.roman.company.Service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final CompanyService companyService;

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
        Page<CourseDTO> page = courseService.findAllCourseWithSort(field, sortDir, currentPage, size);
        int totalPages = page.getTotalPages();
        long totalHorses = page.getTotalElements();
        List<CourseDTO> courseList = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPages);
        model.addAttribute("totalHorses", totalHorses);
        model.addAttribute("sortField", field);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", Sort.Direction.ASC.name().equals(sortDir) ? "DESC" : "ASC");
        model.addAttribute("courses", courseList);
        return "course";
    }

    @GetMapping("/new/{id}")
    public String addVacancyToCompany(Model model, @PathVariable(value = "id") Integer id) {
        Company company = companyService.findCompanyById(id);
        model.addAttribute("course", new CourseDTO());
        model.addAttribute("companyId", company);
        model.addAttribute("location", LocationEnum.values());
        return "new_course";
    }

    @PostMapping
    public String saveVacancy(CourseDTO course) {
        courseService.saveCourse(course);
        return "redirect:/course";
    }

    @GetMapping("/delete/{id}")
    public String deleteVacancy(@PathVariable(value = "id") Integer id) {
        String page;
        try {
            courseService.deleteCourse(id);
            page = "redirect:/course";
        } catch (Exception e) {
            e.printStackTrace();
            page = "courseError";
        }
        return page;
    }

    @GetMapping("/update/{id}")
    public String updateCourse(@PathVariable(value = "id") Integer id, Model model) {
        CourseDTO course = courseService.findCourseById(id);
        model.addAttribute("courseUpdate", course);
        model.addAttribute("location", LocationEnum.values());
        return "update_course";
    }

}
