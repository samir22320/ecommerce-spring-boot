package com.samir.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "cartItemId")
    private Long Id;
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Product product;

    private Integer quantity;
}
