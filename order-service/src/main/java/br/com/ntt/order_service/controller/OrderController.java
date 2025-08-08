package br.com.ntt.order_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @GetMapping
    public ResponseEntity<List<String>> getAllOrders() {
        return ResponseEntity.ok(List.of("Pedido 1", "Pedido 2", "Pedido 3"));
    }
}
