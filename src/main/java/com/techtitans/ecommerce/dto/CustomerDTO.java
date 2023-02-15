package com.techtitans.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techtitans.ecommerce.models.Customer;
import java.time.LocalDate;


public class CustomerDTO {

    private Long id;
    private String firstName;

    private String lastName;

    private String email;

    private String address;

    @JsonFormat(pattern="yyyy/MM/dd")
    private LocalDate birthDate;

    private String phoneNumber;

    private WalletDTO wallet;

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.address = customer.getAddress();
        this.birthDate = customer.getBirthDate();
        this.phoneNumber = customer.getPhoneNumber();
        this.wallet = new WalletDTO(customer.getWallet());
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public WalletDTO getWallet() {
        return wallet;
    }
}
