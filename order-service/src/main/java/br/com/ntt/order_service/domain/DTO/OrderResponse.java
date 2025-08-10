package br.com.ntt.order_service.domain.DTO;

import java.util.Collection;

public record OrderResponse(Collection<ProductDTO> products, double totalPrice) {

}
