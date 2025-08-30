package com.desafio_banco.contaBancaria.repository;

import com.desafio_banco.contaBancaria.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByDocument(String document);
    Optional<User> findByEmail(String email);
    Optional<User> findByDocument(String document);
    Optional<User> findById(long id);
}
