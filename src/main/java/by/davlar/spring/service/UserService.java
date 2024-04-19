package by.davlar.spring.service;

import by.davlar.spring.database.repository.CompanyRepository;
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

import java.util.Optional;

//@Service
@ToString
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void findById(Integer id) {
        this.applicationEventPublisher.publishEvent(
                new DatabaseEvent(this, AccessType.READ, o -> true)
        );
        System.out.println(this.getClass());
    }

    public Optional<UserDto> save(UserDto dto) {
        this.applicationEventPublisher.publishEvent(
                new DatabaseEvent(this, AccessType.CREATE, o -> false)
        );
        if (!UserValidator.validate(dto)) {
            throw new IllegalArgumentException();
        }
        return repository.save(Optional.of(dto)
                        .map(UserMapper::UserDtoToUser)
                        .orElseThrow())
                .map(UserMapper::UserToUserDto);

    }
}
