package com.interpark.ncl.product.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Table(name="PRODUCT")
public class ProductEntity {

    @Id
    @Column (name="ID")
    private Integer id;

    @Column (name="PRICE")
    private Integer price;

    @Column (name="DISCOUNT_PRICE")
    private Integer discountPrice;

    @Column (name="PRODUCT_NAME")
    private String productName;

    @Column (name="RECOMMEND_AGE")
    private Integer recommendAge;

    @Column (name="RECOMMEND_GENDER")
    private String recommendGender;

    @Column (name="BUY_CNT")
    private Integer buyCnt;

}
