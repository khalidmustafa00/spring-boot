package com.bankloan.bank_loan_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // matches BIGINT PRIMARY KEY AUTO_INCREMENT

    @Column(name = "name", nullable = false, length = 100)
    private String name; // matches VARCHAR(100) NOT NULL

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    public Customer() {}

    public Customer(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
