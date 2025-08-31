package com.desafio_banco.contaBancaria.dto;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, long receiverId, long senderId) {
}
