package br.com.ntt.order_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ntt.order_service.domain.DTO.OrderResponse;
import br.com.ntt.order_service.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/simulate")
    public ResponseEntity<OrderResponse> simulateOrder(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(orderService.simulateOrder(ids));
    }
}
