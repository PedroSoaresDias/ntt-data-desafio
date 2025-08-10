package br.com.ntt.product_service.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "tb_products")
@Getter
@Setter
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
    private double price;
}
