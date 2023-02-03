package com.example.rig.mapper;

public interface BaseMapper<T, E>{
    E dtoToEntity(T t);
    T entityToDto(E e);
}
