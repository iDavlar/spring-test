package by.davlar.spring.database.repository;

import by.davlar.spring.database.DatabaseConnection;
import by.davlar.spring.database.entity.UserEntity;
import lombok.ToString;
import org.springframework.stereotype.Repository;

@Repository
@ToString
public class UserRepository extends BaseRepository<UserEntity> {
    public UserRepository(DatabaseConnection connection) {
        super(connection);
    }
}
