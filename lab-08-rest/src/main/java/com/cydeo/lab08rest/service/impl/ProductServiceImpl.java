package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.dto.ProductDTO;
import com.cydeo.lab08rest.entity.Category;
import com.cydeo.lab08rest.entity.Product;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.ProductRepository;
import com.cydeo.lab08rest.service.CategoryService;
import com.cydeo.lab08rest.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
        this.categoryService = categoryService;
    }

    @Override
    public List<ProductDTO> retrieveProductList() {
        return productRepository.findAll().stream().map(product ->
                mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO){
        Product product = productRepository.save(mapperUtil.convert(productDTO, new Product()));
        return mapperUtil.convert(product, new ProductDTO());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productRepository.save(mapperUtil.convert(productDTO, new Product()));
        return mapperUtil.convert(product,new ProductDTO());
    }

    @Override
    public List<ProductDTO> retrieveAllProductByCategoryAndPrice(List<Long> categoryList, BigDecimal price) {
        return productRepository.retrieveProductListByCategory(categoryList,price).stream()
                .map(product -> mapperUtil.convert(product, new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public ProductDTO retrieveByName(String name) {
        return mapperUtil.convert( productRepository.findFirstByName(name), new ProductDTO());
    }

    @Override
    public List<ProductDTO> retrieveProductByTop3ProductPrice() {
        return productRepository.findTop3ByOrderByPriceDesc().stream()
                .map(product -> mapperUtil.convert(product,  new ProductDTO()))
                .collect(Collectors.toList());
    }

    @Override// if business logic already returning integer we don't need to use mapper
    public Integer countProductByPrice(BigDecimal price) {
        return productRepository.countProductByPriceGreaterThan(price);
    }

    @Override
    public List<ProductDTO> retrieveProductByPriceAndQuantity(BigDecimal price, Integer quantity) {
        return productRepository.retrieveProductListGreaterThanPriceAndLowerThanRemainingQuantity(price,
                quantity).stream().map(product -> mapperUtil.convert(product,
                new ProductDTO())).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> retrieveByCategory(Long categoryId) {
        return productRepository.retrieveProductListByCategory(categoryId)
       .stream().map(product -> mapperUtil.convert(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

}


