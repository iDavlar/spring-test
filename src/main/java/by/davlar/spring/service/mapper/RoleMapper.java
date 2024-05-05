package by.davlar.spring.service.mapper;

import by.davlar.spring.database.entity.Role;
import by.davlar.spring.service.dto.RoleDto;

public class RoleMapper {

    public static Role RoleDtoToRole(RoleDto dto) {
        return Role.valueOf(dto.name());
    }

    public static RoleDto RoleToRoleDto(Role entity) {
        return RoleDto.valueOf(entity.name());
    }
}
