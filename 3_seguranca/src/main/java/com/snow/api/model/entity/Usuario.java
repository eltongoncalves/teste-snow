package com.snow.api.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    List<String> roles = new ArrayList<>();

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "usuario_role",
//            joinColumns = @JoinColumn(name = "usuario_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles;
}
