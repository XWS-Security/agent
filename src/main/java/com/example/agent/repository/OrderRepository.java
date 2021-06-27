package com.example.agent.repository;

import com.example.agent.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(value = "SELECT DISTINCT o.id, o.city, o.country, o.street, o.zip_code, o.email, o.name, o.phone_number, o.surname " +
            "FROM product_order AS o, order_item AS oi, product AS p, agent_user AS au " +
            "WHERE o.id = oi.order_id AND oi.product_id = p.id AND p.user_id = au.id AND au.id = :agentId", nativeQuery = true)
    List<Order> findByAgentId(long agentId);
}
