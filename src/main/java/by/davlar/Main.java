package by.davlar;

import by.davlar.spring.config.ApplicationConfiguration;
import by.davlar.spring.service.CompanyService;
import by.davlar.spring.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        var companyService = context.getBean(CompanyService.class);
        var userService = context.getBean(UserService.class);
        companyService.findById(2);
        userService.save();
    }
}