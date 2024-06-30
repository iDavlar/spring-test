package by.davlar.spring.service.mapper;

import by.davlar.spring.database.entity.User;
import by.davlar.spring.service.dto.UserReadDto;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto>{

    @Override
    public UserReadDto map(User object) {
        return UserReadDto.builder()
                .id(object.getId())
                .firstname(object.getFirstname())
                .lastname(object.getLastname())
                .username(object.getUsername())
                .birthdate(object.getBirthdate())
                .role(object.getRole())
                .company(CompanyMapper.CompanyToCompanyDto(object.getCompany()))
                .build();
    }
}
