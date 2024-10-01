package by.davlar.spring.service.dto;

import by.davlar.spring.database.entity.Role;
import org.springframework.security.core.GrantedAuthority;

public enum RoleDto implements GrantedAuthority {
    USER, ADMIN, OPERATOR;

    @Override
    public String getAuthority() {
        return Role.valueOf(this.name())
                .getAuthority();
    }
}
