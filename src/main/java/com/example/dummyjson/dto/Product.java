package com.example.dummyjson.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Product(

        @NotNull
        @Min(0L)
        @Max(999L)
        Long id,

        @NotNull
        String title,

        @NotNull
        String description,

        @NotNull
        Double price
) {
}
