package br.com.ntt.product_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping
    public ResponseEntity<List<String>> getProducts() {
        return ResponseEntity.ok(List.of("Produto A", "Produto B", "Produto C"));
    }
}
