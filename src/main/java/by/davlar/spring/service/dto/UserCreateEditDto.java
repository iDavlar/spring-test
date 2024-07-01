package by.davlar.spring.service.dto;

import by.davlar.spring.database.entity.Role;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Value
@Builder
@FieldNameConstants
public class UserCreateEditDto {
    String username;
    String firstname;
    String lastname;
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
