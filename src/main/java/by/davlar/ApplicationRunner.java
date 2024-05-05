package by.davlar;

import by.davlar.spring.service.CompanyService;
import by.davlar.spring.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {

    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class, args);
        var companyService = context.getBean(CompanyService.class);
        var userService = context.getBean(UserService.class);
        System.out.println(companyService.findById(2).orElseThrow());
//        userService.save(new UserDto(1L, "Test"));
    }
}