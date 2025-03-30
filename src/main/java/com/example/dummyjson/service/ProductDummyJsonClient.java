package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductDummyJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-dummy-json", url = "${dummyjson.product-url}")
public interface ProductDummyJsonClient {

    @GetMapping()
    ProductDummyJson getAllProducts();

    @GetMapping("/{id}")
    Product getProductById(@PathVariable Long id);
}