package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductDummyJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@DisplayName("Teste de unidade da classe ProductService")
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductDummyJsonClient productDummyJsonClient;

    private final Product product1 = new Product(1L, "Product 1", null, null);
    private final Product product2 = new Product(2L, "Product 2", null, null);
    private final ProductDummyJson productDummyJson =
            new ProductDummyJson(List.of(product1, product2), 2, 0, 30);

    @Test
    public void testGetAllProducts() {

        List<Product> products = Arrays.asList(product1, product2);
        when(productDummyJsonClient.getAllProducts()).thenReturn(productDummyJson);

        List<Product> result = productService.getAllProducts();
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).title());
        assertEquals("Product 2", result.get(1).title());
        verify(productDummyJsonClient,times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() {

        when(productDummyJsonClient.getProductById(anyLong())).thenReturn(product1);

        Product result = productService.getProductById(1L);
        assertEquals("Product 1", result.title());
        verify(productDummyJsonClient,times(1)).getProductById(1L);
    }
}
