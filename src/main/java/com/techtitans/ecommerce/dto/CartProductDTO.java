package com.techtitans.ecommerce.dto;

import com.techtitans.ecommerce.models.CartProduct;

public class CartProductDTO {

    private ProductDTO product;

    private Integer quantity;

    private ShoppingCartDTO shoppingCart;

    public CartProductDTO(CartProduct cartProduct) {
        this.product = new ProductDTO(cartProduct.getProduct());
        this.quantity = cartProduct.getQuantity();
        this.shoppingCart = new ShoppingCartDTO(cartProduct.getShoppingCart());
    }

    public ProductDTO getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ShoppingCartDTO getShoppingCart() {
        return shoppingCart;
    }
}
