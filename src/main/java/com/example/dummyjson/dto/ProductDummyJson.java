package com.example.dummyjson.dto;

import java.util.List;

public record ProductDummyJson(
        List<Product> products,
        Integer total,
        Integer skip,
        Integer limit
) {
}
