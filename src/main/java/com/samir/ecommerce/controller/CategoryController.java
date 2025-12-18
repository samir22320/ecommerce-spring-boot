package com.samir.ecommerce.controller;

import com.samir.ecommerce.dto.CategoryDto.CategoryRequest;
import com.samir.ecommerce.dto.CategoryDto.CategoryResponse;
import com.samir.ecommerce.service.CategoryService.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    public final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest)
    {
        CategoryResponse categoryResponse = categoryService.createCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> getAllCategory(@PageableDefault(size =10,sort ="id" ) Pageable pageable)
    {
        Page<CategoryResponse> categoryResponses = categoryService.getAllCategory(pageable);
        return ResponseEntity.ok(categoryResponses);
    }


    @GetMapping("/categoryId/{categoryId}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable("categoryId") Long categoryId)
    {
        CategoryResponse categoryResponse = categoryService.getById(categoryId);
        return ResponseEntity.ok(categoryResponse);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<CategoryResponse> getCategoryByName(@PathVariable("name") String name)
    {
        CategoryResponse categoryResponse = categoryService.getCategoryByName(name);
        return ResponseEntity.ok(categoryResponse);
    }
    @PutMapping("/categoryId/{categoryId}")
    public ResponseEntity<CategoryResponse> updateByID(@Valid @PathVariable("categoryId") Long categoryId,@RequestBody CategoryRequest categoryRequest)
    {
        CategoryResponse categoryResponse = categoryService.updateById(categoryId,categoryRequest);
        return ResponseEntity.ok(categoryResponse);
    }
    @DeleteMapping("/categoryId/{categoryId}")
    public ResponseEntity<Void> deleteById(@PathVariable("categoryId") Long categoryId)
    {
        categoryService.deleteById(categoryId);
        return ResponseEntity.noContent().build();
    }


}
