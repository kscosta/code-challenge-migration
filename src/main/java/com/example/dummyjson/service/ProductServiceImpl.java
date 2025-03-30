package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe responsável pela implementação de serviço de Produtos
 *
 * @version 1.0
 * @since Java 17
 */

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDummyJsonClient productDummyJsonClient;

    public ProductServiceImpl(ProductDummyJsonClient productDummyJsonClient) {

        this.productDummyJsonClient = productDummyJsonClient;
    }

    public Product getProductById(Long id) {

        return productDummyJsonClient.getProductById(id);
    }

    public List<Product> getAllProducts() {

        return productDummyJsonClient.getAllProducts().products();
    }
}