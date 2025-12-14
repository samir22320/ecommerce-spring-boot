package com.samir.ecommerce.service.ProductService;

import com.samir.ecommerce.dto.ProductDto.ProductRequest;
import com.samir.ecommerce.dto.ProductDto.ProductResponse;
import com.samir.ecommerce.entity.Category;
import com.samir.ecommerce.entity.Product;
import com.samir.ecommerce.execption.ResourceNotFoundException;
import com.samir.ecommerce.mapper.ProductMapper;
import com.samir.ecommerce.repository.CategoryRepository;
import com.samir.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    public final ProductMapper productMapper;
    public final CategoryRepository categoryRepository;
    public final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(
                        ()-> new ResourceNotFoundException
                                ("Category id Not found  " +productRequest.getCategoryId()));
        Product product = new Product();
        productMapper.toEntity(productRequest,product,category);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return productMapper.toResponses(products);
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFoundException
                        ("Product id Not found  " + productId));
        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse updateProductById(Long productId, ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(
                        ()-> new ResourceNotFoundException
                                ("Category id Not found  " +productRequest.getCategoryId()));
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFoundException
                        ("Product id Not found  " + productId));
        Product savedProduct = productMapper.toEntity(productRequest,product,category);
        productRepository.save(savedProduct);
        return productMapper.toResponse(savedProduct);

    }

    @Override
    public List<ProductResponse> getByCategoryId(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(
                ()-> new ResourceNotFoundException
                        ("Category id Not found  " +categoryId));
        List<Product> products = productRepository.findAllByCategoryId(categoryId);
        return productMapper.toResponses(products);

    }

    @Override
    public void deleteProductById(Long productId) {
        Product product  = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFoundException
                        ("Product id Not found  " + productId));
        productRepository.delete(product);
    }

}
