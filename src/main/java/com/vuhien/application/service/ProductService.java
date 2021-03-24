package com.vuhien.application.service;

import com.vuhien.application.entity.Product;
import com.vuhien.application.model.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Page<Product> adminGetListProduct(String id,String name,String category,String brand,Integer page);
    Product createProduct(CreateProductRequest createProductRequest);
    void updateProduct(CreateProductRequest createProductRequest, String id);
    Product getProductById(String id);
    void deleteProduct(String[] ids);
}
