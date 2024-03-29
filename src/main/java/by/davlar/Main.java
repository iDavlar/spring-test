package by.davlar;

import by.davlar.spring.database.DatabaseConnection;
import by.davlar.spring.database.repository.UserRepository;
import by.davlar.spring.service.CompanyService;
import by.davlar.spring.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
        var companyService = context.getBean(CompanyService.class);
        var userService = context.getBean(UserService.class);
        companyService.findById(2);
        userService.findById(2);
    }
}