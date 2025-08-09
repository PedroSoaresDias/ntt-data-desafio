package br.com.ntt.order_service.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.ntt.order_service.domain.DTO.OrderResponse;
import br.com.ntt.order_service.domain.DTO.ProductDTO;
import br.com.ntt.order_service.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    private final RestTemplate restTemplate;

    public OrderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public OrderResponse simulateOrder(List<Long> productIds) {
        String idsParam = String.join(",", productIds.stream().map(String::valueOf).toList());
        String url = "http://localhost:8181/products/" + idsParam;

        ResponseEntity<List<ProductDTO>> response = restTemplate.exchange(
            url, 
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<ProductDTO>>() {}
        );

        List<ProductDTO> products = response.getBody();
        double total = products.stream().mapToDouble(ProductDTO::price).sum();

        return new OrderResponse(products, total);
    }

}
