package by.davlar.spring.service;

import by.davlar.spring.database.entity.Image;
import by.davlar.spring.database.entity.User;
import by.davlar.spring.database.repository.ImageRepository;
import by.davlar.spring.database.repository.UserRepository;
import by.davlar.spring.service.dto.ImageCreateEditDto;
import by.davlar.spring.service.dto.ImageReadDto;
import by.davlar.spring.service.mapper.ImageCreateEditMapper;
import by.davlar.spring.service.mapper.ImageReadMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@ToString
@RequiredArgsConstructor
public class ImageService {

    @Value("${app.media.bucket:C:\\JPr\\spring-test\\images}")
    private String bucket;
    private final ImageRepository imageRepository;
    private final ImageReadMapper imageReadMapper;
    private final ImageCreateEditMapper imageCreateEditMapper;
    private final UserRepository userRepository;

    public Optional<ImageReadDto> findById(Long id) {
        return imageRepository.findById(id)
                .map(imageReadMapper::map);
    }

    public List<ImageReadDto> findAllByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return imageRepository.findAllByUser(user).stream()
                .map(imageReadMapper::map)
                .toList();
    }

    @Transactional
    public Optional<ImageReadDto> create(ImageCreateEditDto imageCreateEditDto) {
        return Optional.of(imageCreateEditDto)
                .map(imageCreateEditMapper::map)
                .map(image -> {
                    uploadImage(imageCreateEditDto.getValue());
                    return imageRepository.saveAndFlush(image);
                })
                .map(imageReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return imageRepository.findById(id)
                .map(entity -> {
                    imageRepository.delete(entity);
                    imageRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            upload(
                    image.getOriginalFilename(),
                    image.getInputStream()
            );
        }
    }

    @SneakyThrows
    public void upload(String imagePath, InputStream content) {
        Path imageFullPath = Path.of(bucket, imagePath);

        try (content) {
            Files.createDirectories(imageFullPath.getParent());
            Files.write(
                    imageFullPath,
                    content.readAllBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
        }
    }

    @SneakyThrows
    public Optional<byte[]> get(String imagePath) {
        Path imageFullPath = Path.of(bucket, imagePath);
        return Files.exists(imageFullPath)
                ? Optional.of(Files.readAllBytes(imageFullPath))
                : Optional.empty();
    }

    public ImageReadDto update(Long id, ImageCreateEditDto imageCreateEditDto) {
        return imageRepository.findById(id)
                .map(entity -> {
                    uploadImage(imageCreateEditDto.getValue());
                    return imageCreateEditMapper.map(imageCreateEditDto, entity);
                })
                .map(imageRepository::saveAndFlush)
                .map(imageReadMapper::map)
                .orElseThrow();
    }

    public Optional<byte[]> findContentById(Long id) {
        return imageRepository.findById(id)
                .map(Image::getValue)
                .flatMap(this::get);
    }
}
