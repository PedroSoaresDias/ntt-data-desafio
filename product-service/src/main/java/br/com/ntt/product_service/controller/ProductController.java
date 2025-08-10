package br.com.ntt.product_service.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ntt.product_service.domain.DTO.ProductRequest;
import br.com.ntt.product_service.domain.DTO.ProductResponse;
import br.com.ntt.product_service.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Flux<ProductResponse> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductResponse> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/filter")
    public Flux<ProductResponse> getProductsByIds(@RequestParam Collection<Long> ids) {
        return productService.getAllProductsByIds(ids);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }
}
