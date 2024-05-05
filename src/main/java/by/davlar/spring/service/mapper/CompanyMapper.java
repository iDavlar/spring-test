package by.davlar.spring.service.mapper;

import by.davlar.spring.database.entity.Company;
import by.davlar.spring.service.dto.CompanyDto;

public class CompanyMapper {
    public static Company CompanyDtoToCompany(CompanyDto dto) {
        return Company.builder()
                .id(dto.getId())
                .name(dto.getName())
                .locales(dto.getLocales())
                .build();
    }

    public static CompanyDto CompanyToCompanyDto(Company entity) {
        return CompanyDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .locales(entity.getLocales())
                .build();
    }
}
