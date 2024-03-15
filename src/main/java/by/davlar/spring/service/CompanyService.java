package by.davlar.spring.service;

import by.davlar.spring.database.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends BaseService<CompanyRepository, Integer> {
    public CompanyService(CompanyRepository repository) {
        super(repository);
    }
}
