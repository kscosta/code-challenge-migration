package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductDummyJson;
import com.example.dummyjson.service.ProductDummyJsonClient;
import feign.FeignException;
import feign.Request;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DisplayName("Teste de integração da classe ProductController")
class ProductControllerIntegrationTest {

    @MockBean
    private ProductDummyJsonClient productDummyJsonClient;

    @Autowired
    private MockMvc mockMvc;

    private final Product product1 = new Product(1L, "Product 1", null, 105.0);
    private final Product product2 = new Product(2L, "Product 2", null, 110.0);
    private final ProductDummyJson productDummyJson =
            new ProductDummyJson(List.of(product1, product2), 2, 0, 30);

    @Test
    @DisplayName("A requisição http para recuperar todos os produtos deve retornar a lista de produtos quando executado com sucesso")
    void urlGetAllProductsShouldReturnProductsListWhenSuccessful() throws Exception {

        when(productDummyJsonClient.getAllProducts()).thenReturn(productDummyJson);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(hasSize(2)))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].title").value("Product 2"))
                .andExpect(jsonPath("$[1].price").value(110.0));
    }

    @Test
    @DisplayName("A requisição http para recuperar  produto por id deve retornar a lista de produtos quando executado com sucesso")
    void urlGetProductByIdShouldReturnProductsListWhenSuccessful() throws Exception {

        when(productDummyJsonClient.getProductById(1L)).thenReturn(product1);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Product 1"))
                .andExpect(jsonPath("$.price").value(105.0));
    }

    @Test
    @DisplayName("A requisição http para recuperar  produto por id deve retornar status Not Exists quando não encontrar produto")
    void urlGetProductByIdShouldReturnNotFoundWhenProductNotExists() throws Exception {

        Request request = mock(Request.class);
        when(productDummyJsonClient.getProductById(9999L)).thenThrow(
                new FeignException.NotFound(
                        "[404 Not Found] during [GET] to [https://dummyjson.com/products/9999] [ProductDummyJsonClient#getProductById(Long)]: [{\"message\":\"Product with id '9999' not found\"}]",
                        request, null, null));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/9999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(jsonPath("$").value("Product with id '9999' not found"));
    }
}
