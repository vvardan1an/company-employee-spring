package am.itspace.companyemployeespring.controller;

import am.itspace.companyemployeespring.dto.CreateEmployeeDto;
import am.itspace.companyemployeespring.entity.Company;
import am.itspace.companyemployeespring.entity.Employee;
import am.itspace.companyemployeespring.repository.CompanyRepo;
import am.itspace.companyemployeespring.repository.EmployeeRepo;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Value("${comp.employee.images.path}")
    String folderPath;

    @GetMapping("/employees")
    public String employeePage(ModelMap modelMap) {
        List<Employee> employeeList = employeeRepo.findAll();
        modelMap.addAttribute("employees", employeeList);
        return "employees";
    }

    @GetMapping("/employees/add")
    public String addCompanyPage(ModelMap modelMap) {
        List<Company> companyList = companyRepo.findAll();
        modelMap.addAttribute("companies", companyList);
        return "addEmployee";
    }

    @PostMapping("/employees/add")
    public String addCompany(@ModelAttribute CreateEmployeeDto dto,
                             @RequestParam("image") MultipartFile file) throws IOException {

        Company company = companyRepo.findById(dto.getCompanyId()).orElse(null);
        empControl(dto, file, company);
        return "redirect:/employees";
    }

    @GetMapping("/employees/getImage")
    public @ResponseBody byte[] getImage(@RequestParam("originalFilename") String originalFilename) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + originalFilename);

        return IOUtils.toByteArray(inputStream);
    }

    @GetMapping("/employees/delete")
    public String deleteEmployee(@RequestParam int id) {

        Optional<Employee> employeeRepoById = employeeRepo.findById(id);
        if (employeeRepoById.isPresent()) {
            Company company = employeeRepoById.get().getCompany();
            if (company != null) {
                company.setSize(company.getSize() - 1);
                companyRepo.save(company);
                employeeRepo.deleteById(id);
            }
        }
        return "redirect:/employees";
    }

    private void empControl(CreateEmployeeDto dto, MultipartFile file, Company company) throws IOException {
        if (company != null) {
            Employee employee = Employee.builder()
                    .name(dto.getName())
                    .surname(dto.getSurname())
                    .email(dto.getEmail())
                    .salary(dto.getSalary())
                    .phoneNumber(dto.getPhoneNumber())
                    .position(dto.getPosition())
                    .company(company)
                    .build();

            if (!file.isEmpty() && file.getSize() > 0) {
                String originalFilename = System.nanoTime() + '_' + file.getOriginalFilename();
                File newFile = new File(folderPath + File.separator + originalFilename);
                file.transferTo(newFile);
                employee.setProfilePic(originalFilename);
            }
            employeeRepo.save(employee);
            company.setSize(company.getSize() + 1);
            companyRepo.save(company);
        }
    }
}
