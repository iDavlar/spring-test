package by.davlar.spring.database.repository;

import by.davlar.spring.database.DatabaseConnection;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class CompanyRepository {
    private final DatabaseConnection connection;
}
