package by.davlar.spring.database.repository;

import by.davlar.spring.database.entity.Image;
import by.davlar.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByUser(User user);

}
