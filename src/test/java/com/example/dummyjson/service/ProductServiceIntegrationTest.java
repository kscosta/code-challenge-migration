package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductDummyJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Teste de integração da classe ProductService")
class ProductServiceIntegrationTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductDummyJsonClient productDummyJsonClient;

    private final Product product1 = new Product(1L, "Product 1", null, 105.0);
    private final Product product2 = new Product(2L, "Product 2", null, 110.0);
    private final ProductDummyJson productDummyJson =
            new ProductDummyJson(List.of(product1, product2), 2, 0, 30);

    @Test
    @DisplayName("Método getProductById deve retornar o Produto quando executado com sucesso")
    public void getProductByIdShouldReturnProductWhenSuccessful() {

        when(productDummyJsonClient.getProductById(1L)).thenReturn(product1);

        Product result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Product 1", result.title());
        assertEquals(105.0, result.price());

        verify(productDummyJsonClient).getProductById(1L);
    }

    @Test
    @DisplayName("Método getAllProduct deve retornar lista de Produtos quando executado com sucesso")
    public void getAllProductsShouldReturnProductWhenSuccessful() {

        when(productDummyJsonClient.getAllProducts()).thenReturn(productDummyJson);

        List<Product> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Product 2", result.get(1).title());
        assertEquals(2L, result.get(1).id());
        assertEquals(110.0, result.get(1).price());

        verify(productDummyJsonClient).getAllProducts();
    }

}
