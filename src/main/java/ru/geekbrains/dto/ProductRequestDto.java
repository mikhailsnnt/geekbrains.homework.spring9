package ru.geekbrains.dto;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private Long price;
}
