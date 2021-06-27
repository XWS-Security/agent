package com.example.agent.service;

import com.example.agent.controller.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void createProduct(ProductDto productDto, List<MultipartFile> files) throws IOException;

    void deleteProduct(Long productId);

    ProductDto getProductById(Long productId);

    void updateProduct(ProductDto productDto) throws IOException;

    void updateProductPhoto(ProductDto productDto, List<MultipartFile> files) throws IOException;

    List<ProductDto> getProductsByAgent();

    List<ProductDto> getAllProducts();
}
