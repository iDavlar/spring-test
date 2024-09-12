package by.davlar.spring.service.mapper;

import by.davlar.spring.database.entity.Image;
import by.davlar.spring.database.entity.User;
import by.davlar.spring.database.repository.UserRepository;
import by.davlar.spring.service.dto.ImageCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class ImageCreateEditMapper implements Mapper<ImageCreateEditDto, Image> {

    private final UserRepository userRepository;

    @Override
    public Image map(ImageCreateEditDto object) {
        var user = userRepository.findById(object.getUserId()).orElseThrow();

        return Image.builder()
                .value(multipartFileToString(object.getValue(), ""))
                .user(user)
                .build();
    }

    @Override
    public Image map(ImageCreateEditDto fromObject, Image toObject) {
        var user = toObject.getUser();
        if (!Objects.equals(user.getId(), fromObject.getUserId())) {
            user = userRepository.findById(fromObject.getUserId()).orElseThrow();
        }

        toObject.setValue(
                multipartFileToString(
                        fromObject.getValue(),
                        toObject.getValue()
                )
        );
        toObject.setUser(user);
        return toObject;
    }

    public String multipartFileToString(MultipartFile file, String defaultPath) {
        return Optional.ofNullable(file)
                .filter(Predicate.not(MultipartFile::isEmpty))
                .map(MultipartFile::getOriginalFilename)
                .orElse(defaultPath);
    }
}
