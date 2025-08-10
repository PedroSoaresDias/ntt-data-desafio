package br.com.ntt.product_service.exceptions;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;

import br.com.ntt.product_service.domain.DTO.ErrorResponse;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleProductNotFound(ProductNotFoundException ex,
            ServerWebExchange exchange) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), exchange);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleValidationError(WebExchangeBindException ex,
            ServerWebExchange exchange) {
        String errorMsg = ex.getFieldErrors()
                .stream()
                .map(field -> field.getField() + ": " + field.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMsg, exchange);
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleUnexpectedError(Exception ex, ServerWebExchange exchange) {
        ex.printStackTrace(); // log interno
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado", exchange);
    }

    private Mono<ResponseEntity<ErrorResponse>> buildErrorResponse(HttpStatus status, String message, ServerWebExchange exchange) {
        ErrorResponse error = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                exchange.getRequest().getPath().value(),
                LocalDateTime.now());
        return Mono.just(ResponseEntity.status(status).body(error));
    }
}
