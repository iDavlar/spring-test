package by.davlar.spring.integration;

import by.davlar.spring.database.repository.UserRepository;
import by.davlar.spring.service.UserService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.SpyBean;

@TestConfiguration
public class TestApplicationConfiguration {

//    @SpyBean
//    private DatabaseConnection databaseConnection;
//    @SpyBean
//    private CompanyRepository companyRepository;
//    @SpyBean
//    private UserRepository userRepository;
//    @SpyBean
//    private CompanyService companyService;
    @SpyBean
    private UserRepository userRepository;
}
