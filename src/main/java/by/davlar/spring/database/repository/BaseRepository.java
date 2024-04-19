package by.davlar.spring.database.repository;

import by.davlar.spring.database.DatabaseConnection;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
@RequiredArgsConstructor
@ToString
public abstract class BaseRepository<E> {
    @Autowired
    protected final DatabaseConnection connection;

    public Optional<E> save(E entity) {
        return Optional.of(entity);
    }
}
