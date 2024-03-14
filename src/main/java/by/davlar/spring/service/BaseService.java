package by.davlar.spring.service;

import by.davlar.spring.database.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public abstract class BaseService<R, K> {
    private final R repository;

    public void findById(K id) {
        System.out.println(this.getClass());
    }
}
