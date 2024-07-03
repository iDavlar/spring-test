package by.davlar.spring.service.dto;

import by.davlar.spring.database.entity.Role;
import by.davlar.spring.validator.Birthdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Value
@Builder
@FieldNameConstants
public class UserCreateEditDto {
    @Email
    String username;
    @NotBlank
    String firstname;
    @NotBlank
    String lastname;
    @Birthdate
    LocalDate birthdate;
    Role role;
    Integer companyId;

    public static UserCreateEditDto newEmptyObject() {
        return UserCreateEditDto.builder()
                .username(null)
                .firstname(null)
                .lastname(null)
                .birthdate(LocalDate.now())
                .role(null)
                .companyId(null)
                .build();
    }
}
