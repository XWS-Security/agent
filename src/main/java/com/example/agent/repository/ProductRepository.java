package com.example.agent.repository;

import com.example.agent.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "SELECT id, name, picture, price, quantity FROM product as p, agent_products as ap" +
            " WHERE ap.user_id = :id and p.id = ap.product_id", nativeQuery = true)
    List<Product> findProductsByAgent (@Param("id") Long id);

}
