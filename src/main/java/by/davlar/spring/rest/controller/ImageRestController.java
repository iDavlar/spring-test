package by.davlar.spring.rest.controller;

import by.davlar.spring.service.ImageService;
import by.davlar.spring.service.dto.ImageCreateEditDto;
import by.davlar.spring.service.dto.ImageReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v2/images/")
@RequiredArgsConstructor
public class ImageRestController {
    private final ImageService imageService;

    @GetMapping("/{id}")
    public ImageReadDto findById(@PathVariable Long id) {
        return imageService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}/file")
    public ResponseEntity<byte[]> findContentById(@PathVariable("id") Long id) {
        return imageService.findContentById(id)
                .map(content -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .contentLength(content.length)
                        .body(content))
                .orElseGet(notFound()::build);
    }

    @GetMapping("/byUser/{id}")
    public List<ImageReadDto> findByUserId(@PathVariable Long id) {
        return imageService.findAllByUserId(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ImageReadDto create(@ModelAttribute @Validated ImageCreateEditDto imageCreateEditDto) {
        return imageService.create(imageCreateEditDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/{id}")
    public ImageReadDto update(@PathVariable("id") Long id,
                               @ModelAttribute @Validated ImageCreateEditDto imageCreateEditDto) {

        return imageService.update(id, imageCreateEditDto);
    }

    @PostMapping("/{id}/update")
    public ImageReadDto updatePost(@PathVariable("id") Long id,
                                   @ModelAttribute @Validated ImageCreateEditDto imageCreateEditDto) {

        return imageService.update(id, imageCreateEditDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return imageService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        return imageService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}
