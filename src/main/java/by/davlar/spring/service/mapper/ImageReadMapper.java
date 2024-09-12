package by.davlar.spring.service.mapper;

import by.davlar.spring.database.entity.Image;
import by.davlar.spring.service.dto.ImageReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageReadMapper implements Mapper<Image, ImageReadDto> {

    private final UserReadMapper userReadMapper;

    @Override
    public ImageReadDto map(Image object) {
        return ImageReadDto.builder()
                .id(object.getId())
                .value(object.getValue())
                .user(userReadMapper.map(object.getUser()))
                .build();
    }
}
