package by.davlar.spring.integration.repository;

import by.davlar.spring.annotation.IT;
import by.davlar.spring.database.entity.Role;
import by.davlar.spring.database.entity.User;
import by.davlar.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@IT
@RequiredArgsConstructor
public class UserRepositoryTest {
    private final UserRepository userRepository;

    @Test
    void checkFindAllByRoleIsAndBirthdateBetween_getAllAdminsWithBirthdateBetween_noThrow() {

        List<User> userList = userRepository.findAllByRoleIsAndBirthdateBetween(
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

    @Test
    void checkFindFirst4By_sortingByBirthdate() {
        var users = userRepository.findFirst4By(
                Sort.by("birthdate")
        );
        assertFalse(users.isEmpty());
        assertThat(users).hasSize(4);
    }


    @Test
    void checkFindFirst4By_sortingByBirthdateAndFIO() {
        var users = userRepository.findFirst4By(
                Sort.by("birthdate")
                        .and(Sort.by("firstname")
                                .and(Sort.by("lastname")))
        );
        assertFalse(users.isEmpty());
        assertThat(users).hasSize(4);
    }

    @Test
    void findAllByRole_allUsersWith3UserPerPageSortByFirstname() {
        Role role = Role.USER;

        var pageable = PageRequest.of(
                0,
                3,
                Sort.by("firstname")
        );
        var pages = userRepository.findAllByRole(
                role,
                pageable
        );

        assertThat(pages.getTotalPages()).isNotZero();
        assertThat(pages.getTotalElements()).isNotZero();

        pages.getContent().forEach(user -> System.out.println(user.getFirstname()));
        while (pages.hasNext()) {
            pages = userRepository.findAllByRole(role, pages.nextPageable());
            pages.forEach(user -> System.out.println(user.getFirstname()));
        }
    }

    @Test
    void findAllByRole_allAdminsWith2UserPerPageSortByUsername() {
        Role role = Role.ADMIN;

        var pageable = PageRequest.of(
                0,
                2,
                Sort.by("username")
        );
        var pages = userRepository.findAllByRole(
                role,
                pageable
        );

        assertThat(pages.getTotalPages()).isNotZero();
        assertThat(pages.getTotalElements()).isNotZero();

        pages.getContent().forEach(user -> System.out.println(user.getFirstname()));
        while (pages.hasNext()) {
            pages = userRepository.findAllByRole(role, pages.nextPageable());
            pages.forEach(user -> System.out.println(user.getFirstname()));
        }
    }

    @Test
    void findAllByRole_allUsersWith2UserPerPageSortByFirstnameDescAndLastnameDesc() {
        Role role = Role.USER;

        var pageable = PageRequest.of(
                0,
                2,
                Sort.by("firstname").descending()
                        .and(Sort.by("lastname").descending())
        );
        var pages = userRepository.findAllByRole(
                role,
                pageable
        );

        assertThat(pages.getTotalPages()).isNotZero();
        assertThat(pages.getTotalElements()).isNotZero();

        pages.getContent().forEach(user -> System.out.println(user.getFirstname()));
        while (pages.hasNext()) {
            pages = userRepository.findAllByRole(role, pages.nextPageable());
            pages.forEach(user -> System.out.println(user.getFirstname()));
        }
    }
}
