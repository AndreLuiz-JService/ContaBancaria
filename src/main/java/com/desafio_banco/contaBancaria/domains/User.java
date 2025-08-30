package com.desafio_banco.contaBancaria.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Users")
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String document;
    private String senha;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType type;
    private LocalDateTime createdAt;
}
