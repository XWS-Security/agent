package com.example.agent.service.impl;

import com.example.agent.controller.dto.ProductDto;
import com.example.agent.model.Agent;
import com.example.agent.model.Product;
import com.example.agent.repository.AgentRepository;
import com.example.agent.repository.ProductRepository;
import com.example.agent.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AgentRepository agentRepository;

    @Value("${PROJECT_PATH}")
    private String project_path;

    public ProductServiceImpl(ProductRepository productRepository, AgentRepository agentRepository) {
        this.productRepository = productRepository;
        this.agentRepository = agentRepository;
    }

    @Override
    public void createProduct(ProductDto productDto, List<MultipartFile> files) throws IOException {
        Product product = new Product(productDto.getName(), saveProductPhoto(files), productDto.getPrice(), productDto.getQuantity());
        productRepository.save(product);

        Agent agent = (Agent) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ArrayList<Product> products = (ArrayList<Product>) productRepository.findProductsByAgentId(agent.getId());
        products.add(product);

        agent.setProducts(products);
        agentRepository.save(agent);
    }

    @Override
    public void deleteProduct(Long productId) {
        Agent agent = (Agent) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Product> products = productRepository.findProductsByAgentId(agent.getId());
        products.remove(productRepository.findById(productId).get());
        productRepository.deleteById(productId);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId).get();
        return new ProductDto(product.getId(), product.getName(), product.getPicture(), product.getPrice(), product.getQuantity());
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).get();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        productRepository.save(product);
    }

    @Override
    public void updateProductPhoto(ProductDto productDto, List<MultipartFile> files) throws IOException {
        Product product = productRepository.findById(productDto.getId()).get();
        String photoName = saveProductPhoto(files);
        product.setPicture(photoName);
        productRepository.save(product);
    }

    @Override
    public List<ProductDto> getProductsByAgent() {
        Agent agent = (Agent) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Product> products = productRepository.findProductsByAgentId(agent.getId());
        List<ProductDto> productDtos = new ArrayList<>();

        products.forEach(product -> {
            ProductDto productDto = new ProductDto(product.getId(), product.getName(), product.getPicture(), product.getPrice(), product.getQuantity());
            productDtos.add(productDto);
        });

        return productDtos;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        Iterable<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        products.forEach(product -> {
            ProductDto productDto = new ProductDto(product.getId(), product.getName(), product.getPicture(), product.getPrice(), product.getQuantity());
            productDtos.add(productDto);
        });

        return productDtos;
    }

    private String saveProductPhoto(List<MultipartFile> files) throws IOException {
        MultipartFile file = files.get(0);
        Path post_path = Paths.get(project_path);
        String extension = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
        String photoName = UUID.randomUUID().toString() + "." + extension;
        Files.copy(file.getInputStream(), post_path.resolve(photoName));
        return photoName;
    }
}
