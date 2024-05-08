package by.davlar.spring.integration.repository;

import by.davlar.spring.annotation.IT;
import by.davlar.spring.database.entity.Company;
import by.davlar.spring.database.repository.CompanyRepository;
import by.davlar.spring.database.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@IT
@RequiredArgsConstructor
public class CompanyRepositoryTest {

    private final CompanyRepository companyRepository;

    @Test
    void checkFindByQueries() {
        companyRepository.findByName("Google");

    }

    @Test
    void checkSetFixedNameFor_NoThrow() {
        int id = 1;
        String newName = "Google123";
        companyRepository.setFixedNameFor(newName, id);

        Company google123 = companyRepository.findByName(newName)
                .orElseThrow();
        assertEquals(google123.getName(), newName);
        assertEquals(google123.getId(), id);
        System.out.println(google123);
    }

    @Test
    void checkDeleteAllByNameStartsWith_NoThrow() {
        String namePart = "Test123";
        companyRepository.save(
                Company.builder()
                        .name(namePart + "Meta1")
                        .build()
        );

        companyRepository.save(
                Company.builder()
                        .name(namePart + "Meta2")
                        .build()
        );

        List<Company> companyBeforeDelete = companyRepository.findAllByNameStartingWith(namePart);
        assertThat(companyBeforeDelete).hasSize(2);

        companyRepository.deleteByNameStartingWith(namePart);

        List<Company> companyAfterDelete = companyRepository.findAllByNameStartingWith(namePart);
        assertThat(companyAfterDelete).hasSize(0);
    }
}
