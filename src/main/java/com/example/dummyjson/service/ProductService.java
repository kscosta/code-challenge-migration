package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;

import java.util.List;

/**
 * Interface de serviço de Produtos
 *
 * @version 1.0
 * @since Java 17
 */

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

}