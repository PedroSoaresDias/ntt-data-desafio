package br.com.ntt.order_service.domain.DTO;

import java.util.List;

public record OrderResponse(List<ProductDTO> products, double totalPrice) {

}
