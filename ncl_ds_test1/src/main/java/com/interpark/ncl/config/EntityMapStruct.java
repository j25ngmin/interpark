package com.interpark.ncl.config;

public interface EntityMapStruct<D, E> {

    E toEntity(D dto);

    D toDto(E entity);
}
