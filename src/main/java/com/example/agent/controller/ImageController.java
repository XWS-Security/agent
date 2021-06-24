package com.example.agent.controller;

import com.example.agent.exceptions.BadFileTypeException;
import com.example.agent.service.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/image", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Resource> getImage(@PathVariable String name) {
        try {
            var media = imageService.getImage(name);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Content-Disposition",
                    "attachment; filename=\"" + media.getFile().getName() + "\"");
            responseHeaders.set("Content-Type", media.getType().toString());
            MediaType type = media.getType();
            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .contentLength(media.getFile().length())
                    .contentType(type)
                    .body(media.getStream());
        } catch (BadFileTypeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
//        return new ResponseEntity<>("Invalid characters in request", HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        return new ResponseEntity<>("Invalid characters in request", HttpStatus.BAD_REQUEST);
//    }
}
