package by.davlar.spring.service;

import by.davlar.spring.database.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class CompanyService extends BaseService<CompanyRepository, Integer> {
    public CompanyService(CompanyRepository repository) {
        super(repository);
    }
}
