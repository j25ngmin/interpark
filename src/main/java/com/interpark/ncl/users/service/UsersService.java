package com.interpark.ncl.users.service;

import com.interpark.ncl.users.dto.UsersDto;
import com.interpark.ncl.users.entity.UsersEntity;
import com.interpark.ncl.users.mapper.UsersMapStruct;
import com.interpark.ncl.users.repository.UsersRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    private UsersMapStruct usersMapStruct = Mappers.getMapper(UsersMapStruct.class);

    public UsersDto findById(String userNo) {
        UsersEntity usersEntity = usersRepository.findById(userNo).orElse(null);
        UsersDto usersDto = usersMapStruct.toDto(usersEntity);
        return usersDto;
    }

}
