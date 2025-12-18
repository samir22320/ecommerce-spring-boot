package com.samir.ecommerce.service.CategoryService;

import com.samir.ecommerce.dto.CategoryDto.CategoryRequest;
import com.samir.ecommerce.dto.CategoryDto.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);



    CategoryResponse getById(Long categoryId);

    CategoryResponse getCategoryByName(String name);


    CategoryResponse updateById(Long categoryId, CategoryRequest categoryRequest);

    void deleteById(Long categoryId);


    Page<CategoryResponse> getAllCategory(Pageable pageable);
}
