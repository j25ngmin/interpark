package com.interpark.ncl.users.mapper;

import com.interpark.ncl.config.EntityMapStruct;
import com.interpark.ncl.users.dto.UsersDto;
import com.interpark.ncl.users.entity.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsersMapStruct extends EntityMapStruct<UsersDto, UsersEntity> {
    UsersMapStruct INSTANCE = Mappers.getMapper(UsersMapStruct.class);

}
