package by.davlar.spring.service.mapper;

import by.davlar.spring.database.entity.Company;
import by.davlar.spring.database.entity.User;
import by.davlar.spring.database.repository.CompanyRepository;
import by.davlar.spring.service.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final CompanyRepository companyRepository;

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        toObject.setFirstname(fromObject.getFirstname());
        toObject.setLastname(fromObject.getLastname());
        toObject.setUsername(fromObject.getUsername());
        toObject.setPassword(fromObject.getPassword());
        toObject.setBirthdate(fromObject.getBirthdate());
        toObject.setRole(fromObject.getRole());
        toObject.setCompany(getCompany(fromObject.getCompanyId()));
        return toObject;
    }

    @Override
    public User map(UserCreateEditDto object) {
        return User.builder()
                .firstname(object.getFirstname())
                .lastname(object.getLastname())
                .username(object.getUsername())
                .password(object.getPassword())
                .role(object.getRole())
                .birthdate(object.getBirthdate())
                .company(getCompany(object.getCompanyId()))
                .build();
    }

    private Company getCompany(Integer companyId) {
        return Optional.ofNullable(companyId)
                .flatMap(companyRepository::findById)
                .orElse(null);
    }
}
