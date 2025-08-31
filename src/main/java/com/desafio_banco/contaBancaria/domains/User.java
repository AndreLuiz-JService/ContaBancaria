package com.desafio_banco.contaBancaria.domains;

import com.desafio_banco.contaBancaria.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Users")
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public User(UserDTO userDOT) {
        this.name = userDOT.name();
        this.email = userDOT.email();
        this.document = userDOT.document();
        this.senha = userDOT.senha();
        this.balance = userDOT.balance();
        this.type = userDOT.type();
        this.createdAt = LocalDateTime.now();
    }
}
