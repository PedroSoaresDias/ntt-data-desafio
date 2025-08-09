package br.com.ntt.product_service.service;

import java.util.List;

import br.com.ntt.product_service.domain.DTO.ProductRequest;
import br.com.ntt.product_service.domain.DTO.ProductResponse;

public interface ProductService {
    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    void createProduct(ProductRequest request);

    List<ProductResponse> getAllProductsByIds(List<Long> ids);
}
