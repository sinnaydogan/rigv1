package com.example.rig.service;

import com.example.rig.dto.CustomerDto;
import com.example.rig.request.AuthenticationRequest;
import com.example.rig.request.RegisterRequest;
import com.example.rig.response.AuthenticationResponse;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface CustomerService {
//    CustomerDto save(CustomerDto customerDto);

    void delete(Long id);

    List<CustomerDto> getAll();

    Page<CustomerDto> getAll(Pageable pageable);

    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
