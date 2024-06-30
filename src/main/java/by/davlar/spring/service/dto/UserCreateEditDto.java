package by.davlar.spring.service.dto;

import by.davlar.spring.database.entity.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Value
@FieldNameConstants
public class UserCreateEditDto {
    String username;
    String firstname;
    String lastname;
    LocalDate birthdate;
    Role role;
    Integer companyId;
}
