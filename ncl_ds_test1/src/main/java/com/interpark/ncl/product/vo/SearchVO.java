package com.interpark.ncl.product.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class SearchVO {

    @NotBlank
    private String[] keywords = {};

    private String userNo;

    private String gender;

    private Integer age;

    private List<Integer> notInclude;

}
