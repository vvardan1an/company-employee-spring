package am.itspace.companyemployeespring.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String mainPage(){
        return "index";
    }

    @GetMapping("loginPage")
    public String loginPage(){
        return "loginPage";
    }

}
