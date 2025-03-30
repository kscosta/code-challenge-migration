package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import org.springframework.stereotype.Service;

import java.util.List;

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