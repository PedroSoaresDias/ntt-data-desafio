package br.com.ntt.product_service.domain.repository;

import java.util.Collection;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import br.com.ntt.product_service.domain.model.Product;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Flux<Product> findByIdIn(Collection<Long> ids);
}
