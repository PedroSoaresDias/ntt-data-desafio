package br.com.ntt.order_service.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ntt.order_service.domain.DTO.OrderResponse;
import br.com.ntt.order_service.service.OrderService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/simulate")
    public Flux<OrderResponse> simulateOrder(@RequestParam Collection<Long> ids) {
        return orderService.simulateOrder(ids);
    }
}
