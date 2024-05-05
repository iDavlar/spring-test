package by.davlar.spring.database.repository;

import by.davlar.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //    @Query("select c from Company c " +
//           "join fetch c.locales cl " +
//           "where c.name = :name2")
    Optional<User> findByUsername(String username);


}
