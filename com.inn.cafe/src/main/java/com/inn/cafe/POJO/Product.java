package com.inn.cafe.POJO;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(
        name = "Product.getAllProduct",
        query = "SELECT new com.inn.cafe.wrapper.ProductWrapper(p.id, p.name, p.description, p.price, p.status, p.category.id, p.category.name) FROM Product p"
)

@NamedQuery(
        name = "Product.updateProductStatus",
        query = "UPDATE Product p SET p.status = :status WHERE p.id = :id"
)

@NamedQuery(
        name = "Product.getProductByCategory",
        query = "SELECT new com.inn.cafe.wrapper.ProductWrapper(p.id, p.name) FROM Product p WHERE p.category.id =:id and p.status='true'"
)

@NamedQuery(
        name = "Product.getProductById",
        query = "SELECT new com.inn.cafe.wrapper.ProductWrapper(p.id, p.name, p.description, p.price) FROM Product p WHERE p.id = :id"
)

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "product")
public class Product implements Serializable {

    private static final Long serialVersionUid = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_fk", nullable = false)
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "status")
    private String status;

    public Product() {}

    public Product(Integer id, String name, Category category, String description, Integer price, String status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
