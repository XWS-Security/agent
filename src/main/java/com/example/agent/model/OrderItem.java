package com.example.agent.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "OrderItem")
public class OrderItem {
    @Id
    @SequenceGenerator(name = "order_item_sequence_generator", sequenceName = "order_item_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_sequence_generator")
    @Column(name = "id", unique = true)
    private Long id;

    @OneToOne
    private Product product;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private int price;

    public OrderItem() {
    }

    public OrderItem(Product product, int amount, int price) {
        this.product = product;
        this.amount = amount;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        return amount == orderItem.amount &&
                price == orderItem.price &&
                Objects.equals(id, orderItem.id) &&
                Objects.equals(product, orderItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, amount, price);
    }
}
