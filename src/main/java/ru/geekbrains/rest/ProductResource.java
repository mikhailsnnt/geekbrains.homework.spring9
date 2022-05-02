package ru.geekbrains.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dto.ProductRequestDto;
import ru.geekbrains.dto.ProductResponseDto;
import ru.geekbrains.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/product")
public class ProductResource {
    private final ProductService service;

    @Autowired
    public ProductResource(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductResponseDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable long id ){
        return  service.getById(id);
    }

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto product ){
        return service.save(product);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable long id,@RequestBody ProductRequestDto product){
        return service.update(id,product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        service.delete(id);
    }
}
