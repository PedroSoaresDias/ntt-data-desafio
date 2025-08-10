package br.com.ntt.order_service.service;

import java.util.Collection;

import br.com.ntt.order_service.domain.DTO.OrderResponse;
import reactor.core.publisher.Flux;

public interface OrderService {
    Flux<OrderResponse> simulateOrder(Collection<Long> productIds);
}
