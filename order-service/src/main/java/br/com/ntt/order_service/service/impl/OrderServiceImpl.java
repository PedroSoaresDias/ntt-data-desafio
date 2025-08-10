package br.com.ntt.order_service.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.ntt.order_service.domain.DTO.OrderResponse;
import br.com.ntt.order_service.domain.DTO.ProductDTO;
import br.com.ntt.order_service.service.OrderService;
import reactor.core.publisher.Flux;

@Service
public class OrderServiceImpl implements OrderService {
    private final WebClient webClient;
    private final String productServiceUrl;

    public OrderServiceImpl(WebClient.Builder webClientBuilder, @Value("${product.service.url}") String productServiceUrl) {
        this.webClient = webClientBuilder.build();
        this.productServiceUrl = productServiceUrl;
    }

    @Override
    public Flux<OrderResponse> simulateOrder(Collection<Long> productIds) {
        String idsParam = String.join(",", productIds.stream().map(String::valueOf).toList());
        String url = productServiceUrl + "/products/filter?ids=" + idsParam;

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(ProductDTO.class)
                .collectList()
                .map(products -> {
                    double total = products.stream().mapToDouble(ProductDTO::price).sum();
                    return new OrderResponse(products, total);
                })
                .flux();
    }

}
