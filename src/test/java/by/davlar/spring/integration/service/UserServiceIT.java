package by.davlar.spring.integration.service;

import by.davlar.spring.annotation.IT;
import by.davlar.spring.config.ApplicationConfiguration;
import by.davlar.spring.database.entity.Company;
import by.davlar.spring.database.entity.Role;
import by.davlar.spring.database.entity.User;
import by.davlar.spring.database.repository.CompanyRepository;
import by.davlar.spring.database.repository.UserRepository;
import by.davlar.spring.service.CompanyService;
import by.davlar.spring.service.UserService;
import by.davlar.spring.service.dto.CompanyDto;
import by.davlar.spring.service.dto.RoleDto;
import by.davlar.spring.service.dto.UserDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RequiredArgsConstructor
public class UserServiceIT {

    private final UserService userService;
    private final CompanyService companyService;

    @Test
    void newUserRegistration_NoThrow() {
        CompanyDto google = companyService.findByName("Google").orElseThrow();

        UserDto userDto = UserDto.builder()
                .username("Test")
                .firstname("test")
                .lastname("test")
                .birthDate(LocalDate.now())
                .role(RoleDto.USER)
                .company(google)
                .build();
        Optional<UserDto> result = userService.save(userDto);
        assertTrue(result.isPresent());

        Optional<UserDto> test = userService.findByUsername("Test");
        assertTrue(test.isPresent());

        assertEquals(result, test);

    }
}
