package com.example.dummyjson.exception;

import feign.FeignException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Teste de unidade da classe GlobalExceptionHandler")
@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private FeignException feignException;

    @Test
    @DisplayName("Método feignClientExceptionHandler de retornar Bad Request quando staus do FeignException for 400")
    void feignClientExceptionHandlerShouldReturnBadRequestWhenFeignExceptionHasStatus400() {

        when(feignException.status()).thenReturn(400);
        when(feignException.getMessage()).thenReturn("{\"message\":\"Bad Request\"}");

        ResponseEntity<String> response = globalExceptionHandler.feignClientExceptionHandler(feignException);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Bad Request", response.getBody());
    }

    @Test
    @DisplayName("Método feignClientExceptionHandler de retornar Not Found quando staus do FeignException for 404")
    void feignClientExceptionHandlerShouldReturnNotFoundWhenFeignExceptionHasStatus404() {
        when(feignException.status()).thenReturn(404);
        when(feignException.getMessage()).thenReturn("{\"message\":\"Product with id '9999' not found\"}");

        ResponseEntity<String> response = globalExceptionHandler.feignClientExceptionHandler(feignException);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Product with id '9999' not found", response.getBody());
    }

    @Test
    @DisplayName("Método feignClientExceptionHandler de retornar Internal Server Error quando staus do FeignException for 500")
    void feignClientExceptionHandlerShouldReturnInternalServerErrorWhenFeignExceptionHasOtherStatus() {

        when(feignException.status()).thenReturn(500);
        when(feignException.getMessage()).thenReturn("Internal Server Error");

        ResponseEntity<String> response = globalExceptionHandler.feignClientExceptionHandler(feignException);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal Server Error", response.getBody());
    }
}