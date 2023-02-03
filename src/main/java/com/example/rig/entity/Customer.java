package com.example.rig.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer implements Serializable, UserDetails {
    @Id
    @SequenceGenerator(name = "seq_customer", allocationSize = 1)
    @GeneratedValue(generator = "seq_customer", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, name = "firstname")
    private String firstname;

    @Column(length = 100, name = "lastname")
    private String lastname;

    @Column(length = 100, name = "email")
    private String email;

    @Column(length = 100, name = "password")
    private String password;

    @Column(length = 100, name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
