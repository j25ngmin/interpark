package com.interpark.ncl.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private Integer id;

    private Integer price;

    private Integer discountPrice;

    private String productName;

    private Integer recommendAge;

    private String recommendGender;

    private Integer buyCnt;

    private Integer containCnt = 0;

}
