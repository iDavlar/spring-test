package by.davlar.spring.integration.service;

import by.davlar.spring.annotation.IT;
import by.davlar.spring.database.entity.UserEntity;
import by.davlar.spring.database.repository.UserRepository;
import by.davlar.spring.service.UserService;
import by.davlar.spring.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@IT
@RequiredArgsConstructor
public class UserServiceIT {

    private final UserService userService;
    private final UserRepository userRepository;

    @Test
    void registration() {
        UserEntity user = new UserEntity(1, "Test");
        doReturn(Optional.of(user)).when(userRepository).save(any());

        UserDto userDto = new UserDto(2, "Test2");
        Optional<UserDto> result = userService.save(userDto);
        assertTrue(result.isPresent());

        UserDto userDtoResult = result.orElseThrow();

        assertEquals(userDtoResult.getName(), user.getName());
        assertEquals(userDtoResult.getId(), user.getId());

    }
}
