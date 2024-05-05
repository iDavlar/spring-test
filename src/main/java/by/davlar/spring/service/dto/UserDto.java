package by.davlar.spring.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private RoleDto role;
    private CompanyDto company;
}
