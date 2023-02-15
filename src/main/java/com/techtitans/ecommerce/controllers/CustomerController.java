package com.techtitans.ecommerce.controllers;

import com.techtitans.ecommerce.dto.CustomerDTO;
import com.techtitans.ecommerce.dto.registerDTO.CustomerRegisterDTO;
import com.techtitans.ecommerce.models.Customer;
import com.techtitans.ecommerce.models.Wallet;
import com.techtitans.ecommerce.services.implementations.CustomerServiceImplementation;
import com.techtitans.ecommerce.services.implementations.WalletServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.techtitans.ecommerce.utils.RandomNumberUtils.getRandomNumber5;
import static com.techtitans.ecommerce.utils.VerificationUtils.isMissing;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomerServiceImplementation customerService;
    @Autowired
    WalletServiceImplementation walletService;

    @GetMapping("/customers")
    public List<CustomerDTO> getCustomers(){
        return customerService.getAllCustomers().stream().map(customer -> new CustomerDTO(customer)).collect(Collectors.toList());
    }

    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id){
        return new CustomerDTO(customerService.findCustomerById(id));
    }

    @PostMapping("/customers")
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerRegisterDTO customerRegister){

        if(isMissing(customerRegister.getFirstName())){
            return new ResponseEntity<>("Missing First Name", HttpStatus.FORBIDDEN);
        }
        if (isMissing(customerRegister.getLastName())){
            return new ResponseEntity<>("Missing Last Name", HttpStatus.FORBIDDEN);
        }
        if (isMissing(customerRegister.getEmail())){
            return new ResponseEntity<>("Missing Email", HttpStatus.FORBIDDEN);
        }
        if (isMissing(customerRegister.getPassword())){
            return new ResponseEntity<>("Missing Password", HttpStatus.FORBIDDEN);
        }
        if (isMissing(customerRegister.getAddress())){
            return new ResponseEntity<>("Missing Address", HttpStatus.FORBIDDEN);
        }
        if (isMissing(customerRegister.getPhoneNumber())){
            return new ResponseEntity<>("Missing Phone number", HttpStatus.FORBIDDEN);
        }
        if (customerRegister.getBirthDate().isAfter(LocalDate.now())){
            return new ResponseEntity<>("Wrong birth date", HttpStatus.FORBIDDEN);
        }
        if(customerRegister.getBirthDate().isAfter(LocalDate.now().minusYears(13))){
            return new ResponseEntity<>("Pal lobi", HttpStatus.FORBIDDEN);
        }
        if (isMissing(customerRegister.getBirthDate().toString())){
            return new ResponseEntity<>("Missing birth date", HttpStatus.FORBIDDEN);
        }

        Customer customer = new Customer(customerRegister.getFirstName(),
                                         customerRegister.getLastName(),
                                         customerRegister.getEmail(),
                                         passwordEncoder.encode(customerRegister.getPassword()),
                                         LocalDateTime.now(),
                                         customerRegister.getAddress(),
                                         customerRegister.getBirthDate(),
                                         customerRegister.getPhoneNumber());
        customerService.saveCustomer(customer);

        String walletNumber;
        do {
            Integer number = getRandomNumber5();
            walletNumber = "WALL-" + number.toString();
        }while (walletService.getWalletByNumber(walletNumber) != null);

        Wallet wallet = new Wallet(walletNumber, 0D);
        customer.addWallet(wallet);
        walletService.saveWallet(wallet);
        customerService.saveCustomer(customer);

        return new ResponseEntity<>("Account Created",HttpStatus.CREATED);
    }

    @GetMapping("/customers/current")
    public CustomerDTO getCurrentCustomer(Authentication authentication){
        return new CustomerDTO(customerService.findCustomerByEmail(authentication.getName()));
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomerById(@PathVariable Long id){
        Customer customer = customerService.findCustomerById(id);

        walletService.deleteWalletById(customer.getWallet().getId());
        customerService.deleteCustomerById(customer.getId());
    }

    @DeleteMapping("/customers/current")
    public void deleteCurrentCustomer(Authentication authentication){
        Customer customer = customerService.findCustomerByEmail(authentication.getName());

        walletService.deleteWalletById(customer.getWallet().getId());
        customerService.deleteCustomerById(customer.getId());
    }

    @PatchMapping("/customers/current/password")
    public ResponseEntity<?> changePassword(Authentication authentication,
                                            @RequestParam String newPassword,
                                            @RequestParam String password,
                                            @RequestParam String email) {
        Customer customer = customerService.findCustomerByEmail(authentication.getName());

        if (!(customer.getEmail().equals(email))) {
            return new ResponseEntity<>("Email incorrect",HttpStatus.FORBIDDEN);
        }
        if (passwordEncoder.matches(newPassword, customer.getPassword())) {
            return new ResponseEntity<>("You must enter a different password than the previous one",HttpStatus.FORBIDDEN);
        }
        if (newPassword.length() < 5) {
            return new ResponseEntity<>("Password must contain at least 5 characters", HttpStatus.FORBIDDEN);
        }
        if (!passwordEncoder.matches(password, customer.getPassword())){
            return new ResponseEntity<>("Current password incorrect", HttpStatus.FORBIDDEN);
        }
        if (password.isEmpty() || email.isEmpty() || newPassword.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        customer.setPassword(passwordEncoder.encode(newPassword));
        customerService.saveCustomer(customer);
        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
    }

}
