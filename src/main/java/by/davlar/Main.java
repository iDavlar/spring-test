package by.davlar;

import by.davlar.spring.config.ApplicationConfiguration;
import by.davlar.spring.service.CompanyService;
import by.davlar.spring.service.UserService;
import by.davlar.spring.service.dto.UserDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Main {
    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);
        var companyService = context.getBean(CompanyService.class);
        var userService = context.getBean(UserService.class);
        companyService.findById(2);
        userService.save(new UserDto(1, "Test"));
    }
}