package com.desafio_banco.contaBancaria.service;

import com.desafio_banco.contaBancaria.domains.Transaction;
import com.desafio_banco.contaBancaria.domains.User;
import com.desafio_banco.contaBancaria.dto.TransactionDTO;
import com.desafio_banco.contaBancaria.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestService restService;

    public Transaction createTransaction(TransactionDTO data) throws Exception {
        System.out.println("Creating transaction: " + data);
        User sender = userService.getUserById(data.senderId());
        User receiver = userService.getUserById(data.receiverId());

        userService.validateTransaction(sender, data.value());

        if (!authorizeTransaction()) {
            throw new Exception("Transação não autorizada pelo serviço externo.");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(data.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setOcurredAt(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(data.value()));
        receiver.setBalance(receiver.getBalance().add(data.value()));

        repository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        return newTransaction;
    }

    public boolean authorizeTransaction() throws Exception {
        ResponseEntity<Map> response = restService.get("https://util.devi.tools/api/v2/authorize");

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Erro ao se comunicar com o serviço externo.");
        }


        Map<String, Object> body = response.getBody();
        if (body == null || !body.containsKey("data")) {
            throw new Exception("Resposta inválida do serviço externo.");
        }

        Map<String, Object> data = (Map<String, Object>) body.get("data");
        return Boolean.TRUE.equals(data.get("authorization"));
    }
}
