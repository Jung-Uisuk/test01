package com.springboot.jpa.service.impl;

import com.springboot.jpa.data.dao.dto.ProductDto;
import com.springboot.jpa.data.dao.dto.ProductResponseDto;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;
import com.springboot.jpa.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        LOGGER.info("[getProduct] input number : {}", number);
        Product product = productRepository.findById(number).get();
        LOGGER.info("[getProduct] product number : {}, name : {}", product.getId(),
                product.getName());

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber((product.getId()));
        productResponseDto.setName((product.getName()));
        productResponseDto.setPrice((product.getPrice()));
        productResponseDto.setStock((product.getStock()));
        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto){
        LOGGER.info("[saveProduct] productDTO : {}", productDto.toString());
        Product product = new Product();

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setUpdateAt(LocalDateTime.now());
        product.setCreatedAt(LocalDateTime.now());

        Product saveProduct = productRepository.save(product);
        LOGGER.info("[saveProduct] savedProduct : {}", saveProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber((saveProduct.getId()));
        productResponseDto.setName((saveProduct.getName()));
        productResponseDto.setPrice((saveProduct.getPrice()));
        productResponseDto.setStock((saveProduct.getStock()));

        return productResponseDto;

    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception{

        Product foundproduct = productRepository.findById(number).get();
        foundproduct.setName(name);

        Product changeProduct = productRepository.save(foundproduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber((changeProduct.getId()));
        productResponseDto.setName((changeProduct.getName()));
        productResponseDto.setPrice((changeProduct.getPrice()));
        productResponseDto.setStock((changeProduct.getStock()));

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception{
        productRepository.deleteById(number);
    }
}
