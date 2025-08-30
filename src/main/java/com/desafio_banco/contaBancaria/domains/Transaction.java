package com.desafio_banco.contaBancaria.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Transactions")
@Table(name = "Transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    private LocalDateTime ocurredAt;
}
