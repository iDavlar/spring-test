package by.davlar.spring.integration.service;

import by.davlar.spring.annotation.IT;
import by.davlar.spring.service.CompanyService;
import by.davlar.spring.service.UserService;
import by.davlar.spring.service.dto.CompanyDto;
import by.davlar.spring.service.dto.RoleDto;
import by.davlar.spring.service.dto.UserDto;
import by.davlar.spring.service.dto.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
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
                .password("123")
                .firstname("test")
                .lastname("test")
                .birthdate(LocalDate.now())
                .role(RoleDto.USER)
                .company(google)
                .build();
        Optional<UserDto> result = userService.save(userDto);
        assertTrue(result.isPresent());

        Optional<UserReadDto> test = userService.findByUsername("Test");
        assertTrue(test.isPresent());

        assertEquals(result.get().getId(), test.get().getId());
        assertEquals(result.get().getUsername(), test.get().getUsername());
        assertEquals(result.get().getPassword(), test.get().getPassword());
        assertEquals(result.get().getFirstname(), test.get().getFirstname());
        assertEquals(result.get().getLastname(), test.get().getLastname());
        assertEquals(result.get().getRole().name(), test.get().getRole().name());
        assertEquals(result.get().getCompany(), test.get().getCompany());
        assertEquals(result.get().getBirthdate(), test.get().getBirthdate());


    }

    @Test
    void findAll() {
        List<UserReadDto> users = userService.findAll();
        assertThat(users).hasSize(5);
    }
}
