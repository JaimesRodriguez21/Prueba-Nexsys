package com.nexsys.dao;

import com.nexsys.model.Product;
import com.nexsys.model.request.ProductRequest;

import java.util.List;

public interface ProductDAO {
    List<Product> getAllProducts();

    Long createProduct(ProductRequest request);
}