package com.example.agent.model;

import com.example.agent.exceptions.NotEnoughItemsOnStockException;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence_generator", sequenceName = "product_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence_generator")
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "picture")
    private String picture;

    @Column(name = "price")
    private String price;

    @Column(name = "quantity")
    private int quantity;

    public Product(String name, String picture, String price, int quantity) {
        this.name = name;
        this.picture = picture;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void decreaseQuantity(int n) {
        if (this.quantity < n) throw new NotEnoughItemsOnStockException(name);
        this.quantity -= n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
