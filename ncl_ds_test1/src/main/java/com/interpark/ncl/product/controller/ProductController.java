package com.interpark.ncl.product.controller;

import com.interpark.ncl.product.dto.ProductDto;
import com.interpark.ncl.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/recommended")
    public ResponseEntity<Object> recommendProduct(@RequestParam @NotBlank String keywords,
                                                   @RequestParam(required = false) String userNo) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("keywords", keywords);
        paramMap.put("userNo", userNo);
        List<ProductDto> productDtos = productService.findRecommendedProductBySearch(paramMap);

        if(productDtos.size() == 0) {
            return new ResponseEntity<Object>(productDtos, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Object>(productDtos, HttpStatus.OK);
    }

}
