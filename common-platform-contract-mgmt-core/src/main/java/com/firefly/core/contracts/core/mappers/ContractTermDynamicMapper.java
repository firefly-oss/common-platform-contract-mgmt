package com.firefly.core.contracts.core.mappers;

import com.firefly.core.contracts.interfaces.dtos.ContractTermDynamicDTO;
import com.firefly.core.contracts.models.entities.ContractTermDynamic;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper for ContractTermDynamic entity and ContractTermDynamicDTO
 */
@Mapper(componentModel = "spring")
public interface ContractTermDynamicMapper {

    /**
     * Convert ContractTermDynamic entity to ContractTermDynamicDTO
     *
     * @param contractTermDynamic the ContractTermDynamic entity
     * @return the ContractTermDynamicDTO
     */
    ContractTermDynamicDTO toDTO(ContractTermDynamic contractTermDynamic);

    /**
     * Convert ContractTermDynamicDTO to ContractTermDynamic entity
     *
     * @param contractTermDynamicDTO the ContractTermDynamicDTO
     * @return the ContractTermDynamic entity
     */
    ContractTermDynamic toEntity(ContractTermDynamicDTO contractTermDynamicDTO);
}
