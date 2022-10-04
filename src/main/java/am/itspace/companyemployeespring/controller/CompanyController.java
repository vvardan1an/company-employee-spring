package am.itspace.companyemployeespring.controller;

import am.itspace.companyemployeespring.entity.Company;
import am.itspace.companyemployeespring.repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepo companyRepo;

    @GetMapping("/companies")
    public String companyPage(ModelMap modelMap){
        List<Company> companyList = companyRepo.findAll();
        modelMap.addAttribute("companies",companyList);
        return "companies";
    }

    @GetMapping("/companies/add")
    public String addCompanyPage(){
        return "addCompany";
    }

    @PostMapping("/companies/add")
    public String addCompany(@ModelAttribute Company company){
        companyRepo.save(company);
        return "redirect:/companies";
    }
    @GetMapping("/companies/delete")
    public String deleteCompany(@RequestParam(value = "id")int id){
        companyRepo.deleteById(id);
        return "redirect:/companies";
    }
}
