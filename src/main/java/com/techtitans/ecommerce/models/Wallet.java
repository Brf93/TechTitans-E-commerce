package com.techtitans.ecommerce.models;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "wallet")
@SQLDelete(sql = "UPDATE wallet SET deleted = true WHERE id = ?")
@FilterDef(name = "deletedWallet", parameters = @ParamDef(name = "deleted", type = "boolean"))
@Filter(name = "deletedWallet", condition = "deleted = :deleted")
public class Wallet {

    @Id
    @GeneratedValue(strategy = AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String number;

    private Double balance;

    private Boolean deleted;

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "wallet", fetch = EAGER)
    private Set<ShoppingCart> shoppingCarts = new HashSet<>();

    public Wallet() {
    }

    public Wallet(String number,
                  Double balance) {
        this.number = number;
        this.balance = balance;
        this.deleted = false;
    }

    public void addShoppingCart(ShoppingCart shoppingCart){
        shoppingCart.setWallet(this);
        shoppingCarts.add(shoppingCart);
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }
}
