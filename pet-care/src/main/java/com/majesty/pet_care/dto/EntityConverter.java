package com.majesty.pet_care.dto;

import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class EntityConverter<T, D> {

    private final ModelMapper modelMapper;

    public D mapEntityToDtoD(T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);

    }

    public T mapDtoEntity(D dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

}
