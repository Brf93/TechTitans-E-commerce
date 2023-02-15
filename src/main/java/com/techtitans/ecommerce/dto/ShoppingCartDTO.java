package com.techtitans.ecommerce.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.techtitans.ecommerce.models.CartProduct;
import com.techtitans.ecommerce.models.ShoppingCart;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ShoppingCartDTO {

    private String operationCode;

    @JsonFormat(pattern="yyyy/MM/dd - HH:mm:ss")
    private LocalDateTime creationDate;

    private String paymentMethod;

    private Boolean aprobated;

    private Double total;


    public ShoppingCartDTO(ShoppingCart shoppingCart) {
        this.operationCode = shoppingCart.getOperationCode();
        this.creationDate = shoppingCart.getCreationDate();
        this.paymentMethod = shoppingCart.getPaymentMethod().getName();
        this.aprobated = shoppingCart.getAprobated();
        this.total = shoppingCart.getTotal();
    }

    public String getOperationCode() {
        return operationCode;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Boolean getAprobated() {
        return aprobated;
    }

    public Double getTotal() {
        return total;
    }


}
