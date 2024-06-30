package by.davlar.spring.service.dto;

import by.davlar.spring.database.entity.Role;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
@RequiredArgsConstructor
public class UserReadDto {
    Long id;
    String username;
    String firstname;
    String lastname;
    LocalDate birthdate;
    Role role;
    CompanyDto company;
}
