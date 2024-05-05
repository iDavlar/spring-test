package by.davlar.spring.service;

import by.davlar.spring.database.repository.CompanyRepository;
import by.davlar.spring.listener.AccessType;
import by.davlar.spring.listener.DatabaseEvent;
import by.davlar.spring.service.dto.CompanyDto;
import by.davlar.spring.service.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@ToString
@RequiredArgsConstructor
@Transactional
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Optional<CompanyDto> findById(Integer id) {
        this.applicationEventPublisher.publishEvent(
                new DatabaseEvent(this, AccessType.READ, o -> true)
        );

        return companyRepository.findById(id)
                .map(CompanyMapper::CompanyToCompanyDto);
    }

    public void save(Object dto) {

    }

    public Optional<CompanyDto> findByName(String name) {
        this.applicationEventPublisher.publishEvent(
                new DatabaseEvent(this, AccessType.READ, o -> true)
        );

        return companyRepository.findByName(name)
                .map(CompanyMapper::CompanyToCompanyDto);
    }
}
