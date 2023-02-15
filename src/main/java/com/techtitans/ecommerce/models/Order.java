package com.techtitans.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "ticket")
public class Order {
    @Id
    @GeneratedValue(strategy = AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private Double total;

    private String address;

    private String customerName;

    private String sellerIIBB;

    private String orderNumber;

    private LocalDateTime date;

    private String sellerCUIT;

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    public Order() {
    }

    public Order(Double total,
                 String address,
                 String customerName,
                 String sellerIIBB,
                 String orderNumber,
                 LocalDateTime date,
                 String sellerCUIT) {
        this.total = total;
        this.address = address;
        this.customerName = customerName;
        this.sellerIIBB = sellerIIBB;
        this.orderNumber = orderNumber;
        this.date = date;
        this.sellerCUIT = sellerCUIT;
    }

    public Long getId() {
        return id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSellerIIBB() {
        return sellerIIBB;
    }

    public void setSellerIIBB(String sellerIIBB) {
        this.sellerIIBB = sellerIIBB;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSellerCUIT() {
        return sellerCUIT;
    }

    public void setSellerCUIT(String sellerCUIT) {
        this.sellerCUIT = sellerCUIT;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
