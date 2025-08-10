package br.com.ntt.product_service.service;

import java.util.Collection;

import br.com.ntt.product_service.domain.DTO.ProductRequest;
import br.com.ntt.product_service.domain.DTO.ProductResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductResponse> getAllProducts();

    Mono<ProductResponse> getProductById(Long id);

    Mono<Void> createProduct(ProductRequest request);

    Flux<ProductResponse> getAllProductsByIds(Collection<Long> ids);
}
