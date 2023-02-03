package com.example.rig.mapper;

import com.example.rig.dto.CustomerDto;
import com.example.rig.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
   Customer dtoToEntity(CustomerDto customerDto);

   CustomerDto entityToDto(Customer customer);
}
