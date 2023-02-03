package com.example.rig.service.impl;

import com.example.rig.config.JwtService;
import com.example.rig.dto.CustomerDto;
import com.example.rig.entity.Customer;
import com.example.rig.entity.Role;
import com.example.rig.repository.CustomerRepository;
import com.example.rig.request.AuthenticationRequest;
import com.example.rig.request.RegisterRequest;
import com.example.rig.response.AuthenticationResponse;
import com.example.rig.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

//    private final CustomerMapper mapper;


    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var customer = Customer.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        customerRepository.save(customer);
        var jwtToken = jwtService.generateToken(customer);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var user = customerRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


//    @Override
//    public CustomerDto save(CustomerDto customerDto) {
//        Customer customer = new Customer();
//        customer.setFirstname(customerDto.getFirstname());
//        customer.setLastname(customerDto.getLastname());
//        customer.setEmail(customerDto.getEmail());
//        customer.setPassword(customerDto.getPassword());
//
//        customerRepository.save(customer);
//        return null;
//    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<CustomerDto> getAll() {
        return null;
    }

    @Override
    public Page<CustomerDto> getAll(Pageable pageable) {
        return null;
    }

}
