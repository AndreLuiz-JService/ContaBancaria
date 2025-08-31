package com.desafio_banco.contaBancaria.service;

import com.desafio_banco.contaBancaria.domains.User;
import com.desafio_banco.contaBancaria.domains.UserType;
import com.desafio_banco.contaBancaria.dto.UserDTO;
import com.desafio_banco.contaBancaria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getType() == UserType.MERCHANT) {
            throw new Exception("Os usuarios do tipo lojista não podem realizar transferências.");
        }

        BigDecimal newBalance = sender.getBalance().subtract(amount);

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("Saldo insuficiente para realizar a transferência.");
        }
    }

    public User getUserById(long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado."));
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
