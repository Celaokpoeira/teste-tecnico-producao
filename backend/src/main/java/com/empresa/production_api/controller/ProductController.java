package com.empresa.production_api.controller;

import com.empresa.production_api.model.Product;
import com.empresa.production_api.service.ProductService; // <-- ESTA LINHA É A QUE FALTAVA PARA ELE ACHAR O SERVIÇO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> listAll() {
        return service.findAll();
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }
}