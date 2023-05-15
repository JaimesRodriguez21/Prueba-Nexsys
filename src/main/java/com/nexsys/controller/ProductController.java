package com.nexsys.controller;

import com.nexsys.model.Product;
import com.nexsys.model.request.ProductRequest;
import com.nexsys.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nexsys/v1")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/categories")
    public ResponseEntity createProduct(@RequestBody ProductRequest product) {
        try {
            long pid = productService.createProduct(product);
            Map<String, Long> response = new HashMap<>();
            response.put("pid", pid);
            return ResponseEntity.status(200).body(response);
        } catch (Throwable err) {
            return ResponseEntity.status(500).body(err);
        }
    }

}