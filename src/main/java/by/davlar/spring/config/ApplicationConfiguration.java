package by.davlar.spring.config;

import by.davlar.spring.database.DatabaseConnection;
import by.davlar.spring.database.repository.CompanyRepository;
import by.davlar.spring.database.repository.UserRepository;
import by.davlar.spring.service.CompanyService;
import by.davlar.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("by.davlar.spring")
@PropertySource("classpath:application.properties")
@Import(
        {
                ProdApplicationConfiguration.class,
                TestApplicationConfiguration.class
        }
)
public class ApplicationConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public DatabaseConnection databaseConnection() {
        return new DatabaseConnection(
                env.getProperty("db.username"),
                env.getProperty("db.password"),
                env.getProperty("db.url")
        );
    }

    @Bean
    public CompanyService companyService(CompanyRepository companyRepository, ApplicationEventPublisher applicationEventPublisher) {
        return new CompanyService(companyRepository, applicationEventPublisher);
    }

    @Bean
    public UserService userService(UserRepository userRepository, ApplicationEventPublisher applicationEventPublisher) {
        return new UserService(userRepository, applicationEventPublisher);
    }
}
