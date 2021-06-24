package com.example.agent.controller;

import com.example.agent.controller.dto.ProductDto;
import com.example.agent.service.impl.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/", consumes = {"multipart/form-data"})
    public ResponseEntity<String> createProduct(@RequestPart("obj") @Valid ProductDto productDto, @RequestPart("photos") List<MultipartFile> files) {
        try {
            productService.createProduct(productDto, files);
            return new ResponseEntity<>("Product created!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        try {
            return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDto productDto) {
        try {
            productService.updateProduct(productDto);
            return new ResponseEntity<>("Product updated successfully!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/updatePhoto", consumes = {"multipart/form-data"})
    public ResponseEntity<String> updatePhoto(@RequestPart("obj") @Valid ProductDto productDto, @RequestPart("photos") List<MultipartFile> files) {
        try {
            productService.updateProductPhoto(productDto, files);
            return new ResponseEntity<>("Product updated successfully!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getProduct() {
        try {
            return new ResponseEntity<>(productService.getProductsByAgent(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
