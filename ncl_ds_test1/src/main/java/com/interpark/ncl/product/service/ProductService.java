package com.interpark.ncl.product.service;

import com.interpark.ncl.product.dto.ProductDto;
import com.interpark.ncl.product.entity.ProductEntity;
import com.interpark.ncl.product.mapper.ProductMapStruct;
import com.interpark.ncl.product.repository.ProductRepository;
import com.interpark.ncl.users.dto.UsersDto;
import com.interpark.ncl.users.service.UsersService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("productService")
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UsersService usersService;

    private ProductMapStruct productMapStruct = Mappers.getMapper(ProductMapStruct.class);

    public List<ProductDto> findRecommendedProductBySearch(Map<String, Object> paramMap) {
        List<ProductDto> recommendedProducts = new ArrayList<ProductDto>();

        UsersDto userDto = null;
        if(paramMap.get("userNo") != null) {
            userDto = usersService.findById(paramMap.get("userNo").toString());
        }

        // 키워드
        List<ProductEntity> productEntities = productRepository.findRecommandedProduct(paramMap, PageRequest.of(0, Integer.MAX_VALUE));
        List<ProductDto> productDtos = productEntities.stream().map(productMapStruct::toDto).collect(Collectors.toList());

        if(paramMap.get("keywords") != null) {
            String[] keywords = paramMap.get("keywords").toString().split(",");

            for(ProductDto productDto : productDtos) {
                String productName = productDto.getProductName();
                Integer containCount = 0;
                for(String keyword : keywords) {
                    if(productName.toUpperCase().contains(keyword.toUpperCase())) {
                        containCount += 1;
                    }
                }
                productDto.setContainCnt(containCount);
            }
        }

        // 정렬 및 5개 limit
        productDtos = productDtos.stream().sorted(Comparator.comparing(ProductDto::getContainCnt, Comparator.reverseOrder())
                        .thenComparing(ProductDto::getBuyCnt, Comparator.reverseOrder())    // 구매 수
                        .thenComparing(ProductDto::getId))    // 상품 ID
                .limit(5).collect(Collectors.toList());

        // 사용자 정보
        List<ProductDto> usersProductDtos = new ArrayList<>();
        if(productDtos.size() < 5 && userDto != null) {
            Integer remainCount = 5 - productDtos.size();
            paramMap.put("keywords", null);
            paramMap.put("gender", userDto.getGender());
            paramMap.put("age", userDto.getAge());

            List<Integer> productIds = productDtos.stream().map(object -> object.getId()).collect(Collectors.toList());
            paramMap.put("notInclude", productIds);
            List<ProductEntity> usersProductEntities = productRepository.findRecommandedProduct(paramMap, PageRequest.of(0, Integer.MAX_VALUE));
            usersProductDtos = usersProductEntities.stream().map(productMapStruct::toDto).collect(Collectors.toList());

            for(ProductDto productDto : usersProductDtos) {
                String productName = productDto.getProductName();
                Integer containCount = 0;

                if(userDto.getGender().equals(productDto.getRecommendGender())) {
                    containCount += 1;
                }
                if(userDto.getAge().toString().substring(0, 1).equals(productDto.getRecommendAge().toString().substring(0, 1))) {
                    containCount += 1;
                }

                productDto.setContainCnt(containCount);
            }

            // 정렬 및 5개 limit
            usersProductDtos = usersProductDtos.stream().sorted(Comparator.comparing(ProductDto::getContainCnt, Comparator.reverseOrder()) // 사용자정보 부합
                            .thenComparing(ProductDto::getBuyCnt, Comparator.reverseOrder())  // 구매 수
                            .thenComparing(ProductDto::getId))   // ID
                            .limit(remainCount).collect(Collectors.toList());
        }

        recommendedProducts.addAll(productDtos);
        recommendedProducts.addAll(usersProductDtos);

        // 기본
        if(recommendedProducts.size() < 5) {
            Integer remainCount = 5 - recommendedProducts.size();
            paramMap = new HashMap<>();
            List<Integer> productIds = recommendedProducts.stream().map(object -> object.getId()).collect(Collectors.toList());
            paramMap.put("notInclude", productIds);
            List<ProductEntity> defaultRecommandedEntities = productRepository.findRecommandedProduct(paramMap, PageRequest.of(0, remainCount, Sort.by("buyCnt").descending().and(Sort.by("id").ascending())));
            List<ProductDto> defaultRecommandedDtos = defaultRecommandedEntities.stream().map(productMapStruct::toDto).collect(Collectors.toList());
            recommendedProducts.addAll(defaultRecommandedDtos);
        }

        return recommendedProducts;
    }



}
