package com.example.productservice.Model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category" , uniqueConstraints = {@UniqueConstraint(name = "UK_category_name", columnNames = "category_name")})
public class Category
{
    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Id
    @Column(name = "categoryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "category_name" , unique = true)
    private String category_name;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

    public Category(String category_name)
    {
        this.category_name=category_name;
    }

}
