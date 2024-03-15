package by.davlar.spring.service;

import by.davlar.spring.listener.AccessType;
import by.davlar.spring.listener.DatabaseEvent;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.context.ApplicationEventPublisher;


@ToString
@RequiredArgsConstructor
public abstract class BaseService<R, K extends Number> {
    private final R repository;
    protected final ApplicationEventPublisher applicationEventPublisher;

    public void findById(K id) {
        this.applicationEventPublisher.publishEvent(
                new DatabaseEvent(this, AccessType.READ, o -> true)
        );
        System.out.println(this.getClass());
    }

    public void save() {

    }
}
