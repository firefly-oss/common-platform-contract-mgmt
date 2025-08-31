package com.firefly.core.contracts.core.mappers;

import com.firefly.core.contracts.interfaces.dtos.ContractStatusHistoryDTO;
import com.firefly.core.contracts.models.entities.ContractStatusHistory;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper for ContractStatusHistory entity and ContractStatusHistoryDTO
 */
@Mapper(componentModel = "spring")
public interface ContractStatusHistoryMapper {

    /**
     * Convert ContractStatusHistory entity to ContractStatusHistoryDTO
     *
     * @param contractStatusHistory the ContractStatusHistory entity
     * @return the ContractStatusHistoryDTO
     */
    ContractStatusHistoryDTO toDTO(ContractStatusHistory contractStatusHistory);

    /**
     * Convert ContractStatusHistoryDTO to ContractStatusHistory entity
     *
     * @param contractStatusHistoryDTO the ContractStatusHistoryDTO
     * @return the ContractStatusHistory entity
     */
    ContractStatusHistory toEntity(ContractStatusHistoryDTO contractStatusHistoryDTO);
}
