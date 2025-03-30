package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Teste de unidade da classe ProductController")
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private final Product product1 = new Product(1L, "Product 1", null, null);
    private final Product product2 = new Product(2L, "Product 2", null, null);

    @Test
    public void testGetAllProducts() {

        List<Product> products = List.of(product1, product2);
        when(productService.getAllProducts()).thenReturn(products);

        List<Product> result = productController.getAllProducts().getBody();
        assertTrue(Objects.nonNull(result));
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).title());

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() {

        when(productService.getProductById(1L)).thenReturn(product1);

        Product result = productController.getProductById(1L).getBody();
        assertTrue(Objects.nonNull(result));
        assertEquals("Product 1", result.title());
        verify(productService, times(1)).getProductById(1L);
    }
}
