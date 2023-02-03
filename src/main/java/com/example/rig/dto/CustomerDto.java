package com.example.rig.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
}
