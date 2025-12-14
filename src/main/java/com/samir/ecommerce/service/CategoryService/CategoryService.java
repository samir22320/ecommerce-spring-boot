package com.samir.ecommerce.service.CategoryService;

import com.samir.ecommerce.dto.CategoryDto.CategoryRequest;
import com.samir.ecommerce.dto.CategoryDto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getAllCategory();

    CategoryResponse getById(Long categoryId);

    CategoryResponse getCategoryByName(String name);


    CategoryResponse updateById(Long categoryId, CategoryRequest categoryRequest);

    void deleteById(Long categoryId);
}
