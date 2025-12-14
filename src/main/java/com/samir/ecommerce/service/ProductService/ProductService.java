package com.samir.ecommerce.service.ProductService;

import com.samir.ecommerce.dto.ProductDto.ProductRequest;
import com.samir.ecommerce.dto.ProductDto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProduct();

    ProductResponse getProductById(Long productId);

    ProductResponse updateProductById(Long productId, ProductRequest productRequest);

    List<ProductResponse> getByCategoryId(Long categoryId);

    void deleteProductById(Long productId);
}
