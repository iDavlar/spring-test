package by.davlar.spring.rest.controller;

import by.davlar.spring.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v2/users/")
@RequiredArgsConstructor
public class UserRestController {
    private final ImageService imageService;

    @GetMapping(value = "/{id}/images")
    public List<ResponseEntity<byte[]>> findImages(@PathVariable("id") Long id) {
        return imageService.findAllByUserId(id).stream()
                .map(imageReadDto -> imageService.get(imageReadDto.getValue()).orElse(new byte[0]))
                .map(content -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .contentLength(content.length)
                        .body(content))
                .toList();
    }
}
