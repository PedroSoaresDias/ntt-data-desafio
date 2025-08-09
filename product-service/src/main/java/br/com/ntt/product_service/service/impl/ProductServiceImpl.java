package br.com.ntt.product_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ntt.product_service.domain.DTO.ProductRequest;
import br.com.ntt.product_service.domain.DTO.ProductResponse;
import br.com.ntt.product_service.domain.model.Product;
import br.com.ntt.product_service.domain.repository.ProductRepository;
import br.com.ntt.product_service.exceptions.ProductNotFoundException;
import br.com.ntt.product_service.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> response = products.stream().map(this::toDTO).collect(Collectors.toList());
        
        return response;
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));
        ProductResponse response = toDTO(product);
        return response;
    }

    @Override
    public void createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());

        productRepository.save(product);
    }
    
    private ProductResponse toDTO(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
