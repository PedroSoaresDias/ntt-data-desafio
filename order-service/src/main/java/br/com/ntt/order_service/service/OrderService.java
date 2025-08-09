package br.com.ntt.order_service.service;

import java.util.List;

import br.com.ntt.order_service.domain.DTO.OrderResponse;

public interface OrderService {
    OrderResponse simulateOrder(List<Long> productIds);
}
