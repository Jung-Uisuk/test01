package com.springboot.jpa.controller;

import com.springboot.jpa.data.dao.dto.ChangeProductNameDto;
import com.springboot.jpa.data.dao.dto.ProductDto;
import com.springboot.jpa.data.dao.dto.ProductResponseDto;
import com.springboot.jpa.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value="상품 조회", notes=" 활용")
    @GetMapping()
    public ResponseEntity<ProductResponseDto> getProduct(Long number){
        ProductResponseDto productResponseDto = productService.getProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
        //return ResponseEntity.ok().body(productResponseDto);
    }

    @ApiOperation(value="상품 등록", notes=" 활용")
    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@ApiParam(value="상품DTO") @RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @ApiOperation(value="상품 수정", notes=" 활용")
    @PutMapping()
    public ResponseEntity<ProductResponseDto> changeProduct(@ApiParam(value="상품DTO-이름") @RequestBody ChangeProductNameDto changeProductNameDto) throws Exception{
        ProductResponseDto productResponseDto = productService.changeProductName(changeProductNameDto.getNumber(),changeProductNameDto.getName());
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @ApiOperation(value="상품 삭제", notes=" 활용")
    @DeleteMapping()
    public ResponseEntity<String> deleteProduct( Long number) throws Exception {
        productService.deleteProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}
