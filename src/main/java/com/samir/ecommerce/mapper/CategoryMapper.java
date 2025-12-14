package com.samir.ecommerce.mapper;

import com.samir.ecommerce.dto.CartDto.CartItemResponse;
import com.samir.ecommerce.dto.CategoryDto.CategoryRequest;
import com.samir.ecommerce.dto.CategoryDto.CategoryResponse;
import com.samir.ecommerce.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }
    public List<CategoryResponse> toResponses(List<Category> categories) {
        return categories.stream()
                .map(this::toResponse)
                .toList();
    }

    public Category toEntity(CategoryRequest request,Category category) {
        category.setName(request.getName());
        return category;
    }
}
