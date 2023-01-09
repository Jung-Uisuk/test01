package com.springboot.jpa.service;

import com.springboot.jpa.data.dao.dto.ProductDto;
import com.springboot.jpa.data.dao.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;

}
