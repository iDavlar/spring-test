package by.davlar.spring.database.repository;

import by.davlar.spring.database.DatabaseConnection;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@ToString
public class CompanyRepository {
    private final DatabaseConnection connection;
}
