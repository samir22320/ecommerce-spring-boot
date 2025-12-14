package com.samir.ecommerce.mapper;

import com.samir.ecommerce.dto.ProductDto.ProductRequest;
import com.samir.ecommerce.dto.ProductDto.ProductResponse;
import com.samir.ecommerce.entity.Category;
import com.samir.ecommerce.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setCategoryName(product.getCategory().getName());
        return response;
    }
    public List<ProductResponse> toResponses(List<Product> products) {
        return products.stream()
                .map(this::toResponse)
                .toList();
    }

    public Product toEntity(ProductRequest request,Product product, Category category) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(category);
        return product;
    }
}
