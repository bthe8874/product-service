package com.example.productservice.Model;


import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "products")

public class Product
{

    @Id
    @Column(name = "products_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long products_id;


    @Column(name = "name")
    private  String productName;

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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Product() {
        super();
    }


    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Product(String productName, String productDetail, float productPrice, int stockAvailable , String category_name) {
        super();
        this.productName = productName;
        this.productDetail = productDetail;
        this.productPrice = productPrice;
        this.stockAvailable = stockAvailable;
        this.category_name = category_name;
    }

    public long getId() {
        return products_id;
    }

    public void setId(long id) {
        this.products_id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(int stockAvailable) {
        this.stockAvailable = stockAvailable;
    }
}
