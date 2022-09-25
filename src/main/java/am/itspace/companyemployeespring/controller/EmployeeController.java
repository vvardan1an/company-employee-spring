package am.itspace.companyemployeespring.controller;

import am.itspace.companyemployeespring.entity.Company;
import am.itspace.companyemployeespring.entity.Employee;
import am.itspace.companyemployeespring.repository.CompanyRepo;
import am.itspace.companyemployeespring.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @GetMapping("/employees")
    public String employeePage(ModelMap modelMap){
        List<Employee> employeeList = employeeRepo.findAll();
        modelMap.addAttribute("employees",employeeList);
        return "employees";
    }

    @GetMapping("/employees/add")
    public String addCompanyPage(ModelMap modelMap){
        List<Company> companyList = companyRepo.findAll();
        modelMap.addAttribute("companies",companyList);
        return "addEmployee";
    }

    @PostMapping("/employees/add")
    public String addCompany(@ModelAttribute Employee employee){
            employee.getCompany().setSize(employee.getCompany().getSize() + 1);
            companyRepo.save(employee.getCompany());

        employeeRepo.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/delete")
    public String deleteEmployee(@ModelAttribute Employee employee,
                                 @RequestParam(value = "id") int id){
        employeeRepo.deleteById(id);
        employee.getCompany().setSize(employee.getCompany().getSize() - 1);
        companyRepo.save(employee.getCompany());

        return "redirect:/employees";
    }
}
