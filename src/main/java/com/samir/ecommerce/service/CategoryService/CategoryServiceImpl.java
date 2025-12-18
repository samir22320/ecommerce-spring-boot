package com.samir.ecommerce.service.CategoryService;

import com.samir.ecommerce.dto.CategoryDto.CategoryRequest;
import com.samir.ecommerce.dto.CategoryDto.CategoryResponse;
import com.samir.ecommerce.entity.Category;
import com.samir.ecommerce.execption.ResourceNotFoundException;
import com.samir.ecommerce.mapper.CategoryMapper;
import com.samir.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    public final CategoryMapper categoryMapper;

    public final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        if( categoryRepository.existsByName(categoryRequest.getName()))
            throw new RuntimeException("Category is already saved before");
        Category category = new Category();
        categoryMapper.toEntity(categoryRequest,category);
        categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }








    @Override
    public CategoryResponse getById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category Id Not Found" + categoryId));
        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException("Category name Not Found" + name));

        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse updateById(Long categoryId, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category Id Not Found" + categoryId));
        categoryMapper.toEntity(categoryRequest,category);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    public void deleteById(Long categoryId) {
        Category category =categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category Id is not exist " + categoryId));
        categoryRepository.delete(category);
    }

    @Override
    public Page<CategoryResponse> getAllCategory(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(categoryMapper::toResponse);
    }


}
