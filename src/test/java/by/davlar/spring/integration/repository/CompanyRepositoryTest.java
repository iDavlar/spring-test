package by.davlar.spring.integration.repository;

import by.davlar.spring.annotation.IT;
import by.davlar.spring.database.repository.CompanyRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@IT
@RequiredArgsConstructor
public class CompanyRepositoryTest {

    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Test
    void checkFindByQueries() {
        companyRepository.findByName("Google");

    }
}
