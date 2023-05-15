package com.nexsys.service;

import com.nexsys.dao.ProductDAO;
import com.nexsys.model.Product;
import com.nexsys.model.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductDAO productDAO;

    @Autowired
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public Long createProduct(ProductRequest request) {
        return productDAO.createProduct(request);
    }
}