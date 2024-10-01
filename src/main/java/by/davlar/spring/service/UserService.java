package by.davlar.spring.service;

import by.davlar.spring.database.repository.UserRepository;
import by.davlar.spring.listener.AccessType;
import by.davlar.spring.listener.DatabaseEvent;
import by.davlar.spring.service.dto.UserCreateEditDto;
import by.davlar.spring.service.dto.UserDto;
import by.davlar.spring.service.dto.UserReadDto;
import by.davlar.spring.service.mapper.UserCreateEditMapper;
import by.davlar.spring.service.mapper.UserMapper;
import by.davlar.spring.service.mapper.UserReadMapper;
import by.davlar.spring.service.util.SecurityUser;
import by.davlar.spring.service.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@ToString
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public Optional<UserReadDto> findById(Long id) {
        this.applicationEventPublisher.publishEvent(
                new DatabaseEvent(this, AccessType.READ, o -> true)
        );

        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    public Optional<UserReadDto> findByUsername(String username) {
        this.applicationEventPublisher.publishEvent(
                new DatabaseEvent(this, AccessType.READ, o -> true)
        );

        return userRepository.findByUsername(username)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    @Transactional
    public UserReadDto update(Long id, UserCreateEditDto userCreateEditDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userCreateEditDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new SecurityUser(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole()),
                        user.getId()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }
}
