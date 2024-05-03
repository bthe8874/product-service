package com.example.productservice.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")

public class Product {

    @Id
    @Column(name = "products_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long products_id;


    @Column(name = "name")
    private String productName;

    @Column(name = "description")
    private String productDetail;

    @Column(name = "price")
    private float productPrice;

    @Column(name = "stock")
    private int stockAvailable;


    @Column(name = "category_name")
    private String category_name;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

}