package com.desafio_banco.contaBancaria.repository;

import com.desafio_banco.contaBancaria.domains.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
