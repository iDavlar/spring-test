package by.davlar.spring.database.repository;

import by.davlar.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("select c from Company c " +
           "join fetch c.locales cl " +
           "where c.name = :name2")
    Optional<Company> findByName(@Param("name2") String name);

    @Modifying(clearAutomatically = true)
    @Query("update Company c set c.name = :name where c.id = :id")
    int setFixedNameFor(String name, Integer id);

    void deleteByNameStartingWith(String start);

    List<Company> findAllByNameStartingWith(String start);
}
