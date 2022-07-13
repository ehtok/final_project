package by.roman.company.Controller;

import by.roman.company.DTO.CompanyDTO;
import by.roman.company.DTO.VacancyDTO;
import by.roman.company.Entity.Company;
import by.roman.company.Enum.*;
import by.roman.company.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;


    @GetMapping
    public String findAllCompany(Model model,
                                 @RequestParam(value = "page", required = false, defaultValue = "1")
                                 int currentPage,
                                 @RequestParam(value = "sortField", required = false, defaultValue = "name")
                                 String field,
                                 @RequestParam(value = "sortDir", required = false, defaultValue = "ASC")
                                 String sortDir,
                                 @RequestParam(value = "size", required = false, defaultValue = "5")
                                 int size) {
        Page<CompanyDTO> page = companyService.findAllCompanyWithSort(field, sortDir, currentPage, size);
        int totalPages = page.getTotalPages();
        long totalElement = page.getTotalElements();
        List<CompanyDTO> companyList = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPages);
        model.addAttribute("totalElement", totalElement);
        model.addAttribute("sortField", field);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", Sort.Direction.ASC.name().equals(sortDir) ? "DESC" : "ASC");
        model.addAttribute("companies", companyList);
        return "company";
    }

    @GetMapping(value = "/new")
    public String addCompany(Model model) {
        model.addAttribute("company", new CompanyDTO());
        model.addAttribute("companyType", CompanyTypeEnum.values());
        return "new_company";
    }


    @GetMapping(value = "/search")
    public String searchCompanyByName(Model model) {
        model.addAttribute("company", new CompanyDTO());
        return "search_company";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable(value = "id") Integer id) {
        companyService.deleteCompany(id);
        return "redirect:/company";
    }

    @PostMapping
    public String saveCompany(CompanyDTO company) {
        companyService.saveCompany(company);
        return "redirect:/company";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchCompany(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        String page;
        try {
            CompanyDTO company = companyService.findCompanyByName(name);
            model.addAttribute("company", company);
            page = "search_company";
        } catch (NullPointerException e) {
            e.printStackTrace();
            page = "companyNameError";
        }
        return page;
    }

    @GetMapping("/update/{id}")
    public String updateCompany(@PathVariable(value = "id") Integer id, Model model) {
        CompanyDTO company = companyService.findCompanyDTOById(id);
        model.addAttribute("company", company);
        model.addAttribute("companyType", CompanyTypeEnum.values());
        return "update_company";
    }


}
