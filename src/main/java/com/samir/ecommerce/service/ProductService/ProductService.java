package com.samir.ecommerce.service.ProductService;

import com.samir.ecommerce.dto.ProductDto.ProductRequest;
import com.samir.ecommerce.dto.ProductDto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long productId);

    ProductResponse updateProductById(Long productId, ProductRequest productRequest);

    void deleteProductById(Long productId);

    Page<ProductResponse> getAllProduct(Pageable pageable);

    Page<ProductResponse> getByCategoryId(Long categoryId, Pageable pageable);
}
