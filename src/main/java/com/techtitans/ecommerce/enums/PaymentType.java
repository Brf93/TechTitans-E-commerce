package com.techtitans.ecommerce.enums;

public enum PaymentType {

    CREDITCARD("Credit Card"),
    DEBITCARD("Debit Card"),
    CBU("CBU"),
    MERCADOPAGO("Mercado Pago");

    private String name;

    PaymentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
