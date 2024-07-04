package com.interpark.ncl.product.repository;

import com.interpark.ncl.product.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepositoryCustom {
    List<ProductEntity> findRecommandedProduct(Map<String, Object> paramMap, Pageable pageable);
}
