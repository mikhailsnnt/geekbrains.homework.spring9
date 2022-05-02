package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.NotFoundException;
import ru.geekbrains.dto.ProductRequestDto;
import ru.geekbrains.dto.ProductResponseDto;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return repository.findAll()
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }


    @Override
    public ProductResponseDto getById(long id) {
        return mapToDto( repository.findById(id)
                .orElseThrow(()->new NotFoundException(String.format("Product with id %d not found",id)))
        );
    }

    @Override
    public ProductResponseDto save(ProductRequestDto dto) {
        return mapToDto(repository.save(mapToEntity(dto)));
    }

    @Override
    public ProductResponseDto update(long id, ProductRequestDto dto) {
        Product product = mapToEntity(dto);
        product.setId(id);
        return mapToDto(repository.save(product));
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    private ProductResponseDto mapToDto(Product product) {
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice());
    }

    private Product mapToEntity(ProductRequestDto requestDto){
        Product product = new Product();
        product.setName(requestDto.getName());
        product.setPrice(requestDto.getPrice());
        return product;
    }
}
