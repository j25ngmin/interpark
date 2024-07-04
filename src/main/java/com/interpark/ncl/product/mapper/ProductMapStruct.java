package com.interpark.ncl.product.mapper;

import com.interpark.ncl.config.EntityMapStruct;
import com.interpark.ncl.product.dto.ProductDto;
import com.interpark.ncl.product.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapStruct extends EntityMapStruct<ProductDto, ProductEntity> {
    ProductMapStruct INSTANCE = Mappers.getMapper(ProductMapStruct.class);

    @Mapping(target = "containCnt", ignore = true)
    ProductDto toDto(ProductEntity productEntity);

    ProductEntity toEntity(ProductDto productDto);

}
