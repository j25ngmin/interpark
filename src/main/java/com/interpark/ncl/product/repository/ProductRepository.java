package com.interpark.ncl.product.repository;

import com.interpark.ncl.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>, QuerydslPredicateExecutor<ProductEntity>, ProductRepositoryCustom {


}
