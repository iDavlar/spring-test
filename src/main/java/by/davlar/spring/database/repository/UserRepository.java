package by.davlar.spring.database.repository;

import by.davlar.spring.database.entity.Role;
import by.davlar.spring.database.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findAllByRoleIsAndBirthdateBetween(Role role, LocalDate startDate, LocalDate endDate);

    List<User> findFirst4By(Sort sort);

    Page<User> findAllByRole(Role role, Pageable pageable);
}
