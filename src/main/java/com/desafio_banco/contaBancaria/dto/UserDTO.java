package com.desafio_banco.contaBancaria.dto;

import com.desafio_banco.contaBancaria.domains.UserType;

import java.math.BigDecimal;

public record UserDTO(String name, String email, String document, String senha, BigDecimal balance, UserType type) {
}
