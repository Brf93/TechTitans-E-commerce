package com.techtitans.ecommerce.dto.registerDTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public class CustomerRegisterDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String address;

    @JsonFormat(shape = STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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
}
