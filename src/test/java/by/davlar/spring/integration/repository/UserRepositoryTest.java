package by.davlar.spring.integration.repository;

import by.davlar.spring.annotation.IT;
import by.davlar.spring.database.entity.Role;
import by.davlar.spring.database.entity.User;
import by.davlar.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@IT
@RequiredArgsConstructor
public class UserRepositoryTest {
    private final UserRepository userRepository;

    @Test
    void checkFindAllByRoleIsAndBirthDateBetween_getAllAdminsWithBirthdateBetween_noThrow() {

        List<User> userList = userRepository.findAllByRoleIsAndBirthDateBetween(
                Role.ADMIN,
                LocalDate.of(1980, 1, 1),
                LocalDate.of(1990, 1, 1)
        );

        assertThat(userList).hasSize(1);

        List<String> nameList = userList.stream()
                .map(User::getUsername)
                .toList();

        assertThat(nameList).contains("kate@gmail.com");

    }
}
