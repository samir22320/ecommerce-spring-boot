package com.samir.ecommerce.controller;

import com.samir.ecommerce.dto.ProductDto.ProductRequest;
import com.samir.ecommerce.dto.ProductDto.ProductResponse;
import com.samir.ecommerce.service.ProductService.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")

public class ProductController {
    final
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest)
    {
        ProductResponse productResponse = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProduct
            (@PageableDefault(size = 10,sort = "price",direction = Sort.Direction.DESC)Pageable pageable)
    {
        Page<ProductResponse> productResponses = productService.getAllProduct(pageable);
        return ResponseEntity.ok(productResponses);

    }

    @GetMapping("/productId/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("productId")Long productId)
    {
        ProductResponse productResponse = productService.getProductById(productId);
        return ResponseEntity.ok(productResponse);
    }
    @PutMapping("/productId/{productId}")
    public ResponseEntity<ProductResponse> updateProductById(
            @Valid @PathVariable("productId")Long productId, @RequestBody ProductRequest productRequest)
    {
        ProductResponse productResponse = productService.updateProductById(productId,productRequest);
        return ResponseEntity.ok(productResponse);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<ProductResponse>> getByCategoryId
    (@PathVariable("categoryId") Long categoryId , @PageableDefault(size = 10,sort = "price"
            ,direction = Sort.Direction.DESC) Pageable pageable)
    {
        Page<ProductResponse> productResponses = productService.getByCategoryId(categoryId,pageable);
        return ResponseEntity.ok(productResponses);
    }



    @DeleteMapping("/productId/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("productId") Long productId)
    {
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }

}
