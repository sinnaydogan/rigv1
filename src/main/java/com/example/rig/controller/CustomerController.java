package com.example.rig.controller;

import com.example.rig.dto.CustomerDto;
import com.example.rig.request.AuthenticationRequest;
import com.example.rig.request.RegisterRequest;
import com.example.rig.response.AuthenticationResponse;
import com.example.rig.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

//    @PostMapping
//    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto customerDto) {
//        return ResponseEntity.ok(customerService.save(customerDto));
//    }

    @GetMapping("/get")
    public ResponseEntity<List<CustomerDto>> get() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(customerService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(customerService.authenticate(request));
    }
}
