package com.techtitans.ecommerce.models;

import com.techtitans.ecommerce.enums.ProductType;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted = true WHERE id = ?")
@FilterDef(name = "deletedProduct", parameters = @ParamDef(name = "deleted", type = "boolean"))
@Filter(name = "deletedProduct", condition = "deleted = :deleted")
public class Product {
    @Id
    @GeneratedValue(strategy = AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String name;

    private Double price;

    private String code;

    private String description;

    private Boolean deleted;

    private Integer stock;

    private String brand;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @ElementCollection
    private List<String> image = new ArrayList<>();

    @ElementCollection
    private Set<String> categories = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<CartProduct> cartProducts = new HashSet<>();
    public Product() {
    }

    public Product(String name,
                   Double price,
                   String code,
                   String description,
                   Integer stock,
                   String brand,
                   Set<String> categories,
                   ProductType type,
                   List<String> image) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.description = description;
        this.deleted = false;
        this.stock = stock;
        this.brand = brand;
        this.categories = categories;
        this.type = type;
        this.image = image;
    }

    public void addCartProduct(CartProduct cartProduct){
        cartProduct.setProduct(this);
        cartProducts.add(cartProduct);
    }
    public void addCategory(String string){
        categories.add(string);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public Set<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public ProductType getProductType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }


}
