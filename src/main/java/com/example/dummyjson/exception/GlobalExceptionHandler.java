package com.example.dummyjson.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe responsável por gerenciar globalmente as exceções da aplicação
 *
 * @version 1.0
 * @since Java 17
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> feignClientExceptionHandler(
            FeignException feignException) {

        String message = feignException.getMessage();

        if (feignException.getMessage().contains("message")) {
            Pattern pattern = Pattern.compile("\"message\":\"([^\"]+)\"");
            Matcher matcher = pattern.matcher(message);
           while (matcher.find()) { message = matcher.group(1); }
        }

        return switch (feignException.status()) {
            case 400 -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            case 404 -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        };

    }

}
