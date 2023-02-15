package com.techtitans.ecommerce.configurations;

import com.techtitans.ecommerce.models.Customer;
import com.techtitans.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    CustomerRepository customerRepository;

    @Bean
    public PasswordEncoder passwordEncoder(){return PasswordEncoderFactories.createDelegatingPasswordEncoder();}


    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(email -> {
            Customer customer = customerRepository.findByEmail(email);

            if (customer != null || (! customer.getDeleted())){
                if (customer.getEmail().contains("@admin")){
                    return new User(customer.getEmail(),
                            customer.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMIN", "CLIENT"));
                }
                return new User(customer.getEmail(),
                        customer.getPassword(),
                        AuthorityUtils.createAuthorityList( "CLIENT"));
            }else{
                throw new UsernameNotFoundException("Unknown user with email: " + email);
            }
        });
    }
}
