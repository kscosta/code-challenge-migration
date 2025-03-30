package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

}