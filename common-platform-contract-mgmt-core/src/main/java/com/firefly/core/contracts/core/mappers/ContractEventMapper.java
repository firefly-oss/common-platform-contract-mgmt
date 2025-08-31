package com.firefly.core.contracts.core.mappers;

import com.firefly.core.contracts.interfaces.dtos.ContractEventDTO;
import com.firefly.core.contracts.models.entities.ContractEvent;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper for ContractEvent entity and ContractEventDTO
 */
@Mapper(componentModel = "spring")
public interface ContractEventMapper {

    /**
     * Convert ContractEvent entity to ContractEventDTO
     *
     * @param contractEvent the ContractEvent entity
     * @return the ContractEventDTO
     */
    ContractEventDTO toDTO(ContractEvent contractEvent);

    /**
     * Convert ContractEventDTO to ContractEvent entity
     *
     * @param contractEventDTO the ContractEventDTO
     * @return the ContractEvent entity
     */
    ContractEvent toEntity(ContractEventDTO contractEventDTO);
}
