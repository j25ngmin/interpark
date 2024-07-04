package com.interpark.ncl.product.repository;

import com.interpark.ncl.config.CommonUtil;
import com.interpark.ncl.product.entity.ProductEntity;
import com.interpark.ncl.product.entity.QProductEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public ProductRepositoryImpl() {
        super(ProductRepositoryImpl.class);
    }

    @Override
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }

    public List<ProductEntity> findRecommandedProduct(Map<String, Object> map, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(this.getEntityManager());

        QProductEntity qProduct = new QProductEntity("productEntity");

        BooleanBuilder builder = new BooleanBuilder();
        BooleanBuilder innerbuilder = new BooleanBuilder();
        if(map.get("keywords") != null) {
            String[] keywords = map.get("keywords").toString().split(",");
            for(String keyword : keywords) {
                innerbuilder.or(qProduct.productName.toUpperCase().contains(keyword.toUpperCase()));
            }
            builder.and(innerbuilder);
        }

        BooleanBuilder innerBuilder2 = new BooleanBuilder();
        if(map.get("age") != null || map.get("age") != null) {
            if (map.get("gender") != null) {
                innerBuilder2.and(qProduct.recommendGender.eq(map.get("gender").toString()));
            }

            if (map.get("age") != null) {
                innerBuilder2.and(Expressions.stringPath(qProduct.recommendAge.toString()).substring(0, 1).eq(map.get("age").toString().substring(0, 1)));
            }
            builder.and(innerBuilder2);
        }

        if(map.get("notInclude") != null) {
            List<Integer> includeProduct = (List<Integer>) map.get("notInclude");
            builder.and(qProduct.id.notIn(includeProduct));
        }

        List<ProductEntity> result = queryFactory.selectFrom(qProduct)
                .where(builder)
                .orderBy(CommonUtil.getOrderSpecifiers(pageable.getSort(), ProductEntity.class))
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();

        return result;
    }

}
