package com.catalis.core.contract.core.mappers.events.v1;

import com.catalis.core.contract.interfaces.dtos.events.v1.ContractEventDTO;
import com.catalis.core.contract.models.entities.events.v1.ContractEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractEventMapper {
    ContractEventDTO toDto(ContractEvent entity);
    ContractEvent toEntity(ContractEventDTO dto);
}
