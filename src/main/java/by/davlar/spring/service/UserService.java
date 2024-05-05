package by.davlar.spring.service;

import by.davlar.spring.database.repository.UserRepository;
import by.davlar.spring.listener.AccessType;
import by.davlar.spring.listener.DatabaseEvent;
import by.davlar.spring.service.dto.UserDto;
import by.davlar.spring.service.mapper.UserMapper;
import by.davlar.spring.service.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@ToString
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Optional<UserDto> findById(Long id) {
        this.applicationEventPublisher.publishEvent(
                new DatabaseEvent(this, AccessType.READ, o -> true)
        );

        return userRepository.findById(id)
                .map(UserMapper::UserToUserDto);
    }

    public Optional<UserDto> findByUsername(String username) {
        this.applicationEventPublisher.publishEvent(
                new DatabaseEvent(this, AccessType.READ, o -> true)
        );

        return userRepository.findByUsername(username)
                .map(UserMapper::UserToUserDto);
    }

    public Optional<UserDto> save(UserDto dto) {
        this.applicationEventPublisher.publishEvent(
                new DatabaseEvent(this, AccessType.CREATE, o -> false)
        );
        if (!UserValidator.validate(dto)) {
            throw new IllegalArgumentException();
        }
        return Optional.of(userRepository.save(Optional.of(dto)
                        .map(UserMapper::UserDtoToUser)
                        .orElseThrow()))
                .map(UserMapper::UserToUserDto);

    }
}
