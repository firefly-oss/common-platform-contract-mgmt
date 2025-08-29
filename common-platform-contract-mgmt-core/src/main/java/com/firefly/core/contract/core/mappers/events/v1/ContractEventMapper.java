package com.firefly.core.contract.core.mappers.events.v1;

import com.firefly.core.contract.interfaces.dtos.events.v1.ContractEventDTO;
import com.firefly.core.contract.models.entities.events.v1.ContractEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractEventMapper {
    ContractEventDTO toDto(ContractEvent entity);
    ContractEvent toEntity(ContractEventDTO dto);
}
