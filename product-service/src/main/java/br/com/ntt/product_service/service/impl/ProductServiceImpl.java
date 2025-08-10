package br.com.ntt.product_service.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import br.com.ntt.product_service.domain.DTO.ProductRequest;
import br.com.ntt.product_service.domain.DTO.ProductResponse;
import br.com.ntt.product_service.domain.model.Product;
import br.com.ntt.product_service.domain.repository.ProductRepository;
import br.com.ntt.product_service.exceptions.ProductNotFoundException;
import br.com.ntt.product_service.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Flux<ProductResponse> getAllProducts() {
        return productRepository.findAll().map(this::toDTO);
    }

    @Override
    public Mono<ProductResponse> getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::toDTO).switchIfEmpty(Mono.error(new ProductNotFoundException("Produto não encontrado")));
    }
    
    @Override
    public Flux<ProductResponse> getAllProductsByIds(Collection<Long> ids) {
        return productRepository.findByIdIn(ids).map(this::toDTO);
    }

    @Override
    public Mono<Void> createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());

        return productRepository.save(product).then();
    }
    
    private ProductResponse toDTO(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

}
