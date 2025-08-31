package com.firefly.core.contracts.core.mappers;

import com.firefly.core.contracts.interfaces.dtos.ContractDTO;
import com.firefly.core.contracts.models.entities.Contract;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper for Contract entity and ContractDTO
 */
@Mapper(componentModel = "spring")
public interface ContractMapper {

    /**
     * Convert Contract entity to ContractDTO
     *
     * @param contract the Contract entity
     * @return the ContractDTO
     */
    ContractDTO toDTO(Contract contract);

    /**
     * Convert ContractDTO to Contract entity
     *
     * @param contractDTO the ContractDTO
     * @return the Contract entity
     */
    Contract toEntity(ContractDTO contractDTO);
}
