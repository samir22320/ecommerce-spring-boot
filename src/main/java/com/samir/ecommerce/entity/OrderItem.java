package com.samir.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "orderitemId")
    private Long id;
    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;

    private Integer quantity;

    private Double priceAtPurchase;

}
