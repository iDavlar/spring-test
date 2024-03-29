package by.davlar.spring.service;

import by.davlar.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
public class UserService extends BaseService<UserRepository, Integer> {
    public UserService(UserRepository repository) {
        super(repository);
    }
}
