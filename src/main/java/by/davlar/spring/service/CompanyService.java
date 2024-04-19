package by.davlar.spring.service;

import by.davlar.spring.database.repository.BaseRepository;
import by.davlar.spring.database.repository.CompanyRepository;
import by.davlar.spring.listener.AccessType;
import by.davlar.spring.listener.DatabaseEvent;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
@ToString
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository repository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void findById(Integer id) {
        this.applicationEventPublisher.publishEvent(
                new DatabaseEvent(this, AccessType.READ, o -> true)
        );
        System.out.println(this.getClass());
    }

    public void save(Object dto) {

    }
}
