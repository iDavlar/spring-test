package by.davlar.spring.service;

import by.davlar.spring.database.repository.UserRepository;
import by.davlar.spring.listener.AccessType;
import by.davlar.spring.listener.DatabaseEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<UserRepository, Integer> {


    public UserService(UserRepository repository, ApplicationEventPublisher applicationEventPublisher) {
        super(repository, applicationEventPublisher);
    }

    @Override
    public void save() {
        this.applicationEventPublisher.publishEvent(
                new DatabaseEvent(this, AccessType.CREATE, o -> false)
        );
        super.save();
    }
}
