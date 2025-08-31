package com.desafio_banco.contaBancaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RestService {
    @Autowired
    private RestTemplate externalService;

    public ResponseEntity<Map> get(String url) {
        ResponseEntity<Map> response = externalService.getForEntity(
                "https://util.devi.tools/api/v2/authorize",
                Map.class
        );

        return response;
    }

    public ResponseEntity<Map> get(String url, Object params) {
        ResponseEntity<Map> response = externalService.getForEntity(
                "https://util.devi.tools/api/v2/authorize",
                Map.class,
                params
        );

        return response;
    }
}
