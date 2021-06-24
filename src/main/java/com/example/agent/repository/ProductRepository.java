package com.example.agent.repository;

import com.example.agent.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE user_id = :id", nativeQuery = true)
    List<Product> findProductsByAgentId(@Param("id") Long id);
}
