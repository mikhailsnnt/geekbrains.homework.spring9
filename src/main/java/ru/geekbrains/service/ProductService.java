package ru.geekbrains.service;

import ru.geekbrains.dto.ProductRequestDto;
import ru.geekbrains.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAll();

    ProductResponseDto getById(long id);

    ProductResponseDto save(ProductRequestDto dto);

    ProductResponseDto update(long id, ProductRequestDto product);

    void delete(long id);

}
