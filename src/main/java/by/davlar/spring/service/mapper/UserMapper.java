package by.davlar.spring.service.mapper;

import by.davlar.spring.database.entity.User;
import by.davlar.spring.service.dto.UserDto;

public class UserMapper {
    public static User UserDtoToUser(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .birthDate(dto.getBirthDate())
                .role(RoleMapper.RoleDtoToRole(dto.getRole()))
                .company(CompanyMapper.CompanyDtoToCompany(dto.getCompany()))
                .build();
    }

    public static UserDto UserToUserDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .birthDate(entity.getBirthDate())
                .role(RoleMapper.RoleToRoleDto(entity.getRole()))
                .company(CompanyMapper.CompanyToCompanyDto(entity.getCompany()))
                .build();
    }
}
