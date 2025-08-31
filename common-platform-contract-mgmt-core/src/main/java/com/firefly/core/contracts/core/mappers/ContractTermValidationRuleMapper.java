package com.firefly.core.contracts.core.mappers;

import com.firefly.core.contracts.interfaces.dtos.ContractTermValidationRuleDTO;
import com.firefly.core.contracts.models.entities.ContractTermValidationRule;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper for ContractTermValidationRule entity and ContractTermValidationRuleDTO
 */
@Mapper(componentModel = "spring")
public interface ContractTermValidationRuleMapper {

    /**
     * Convert ContractTermValidationRule entity to ContractTermValidationRuleDTO
     *
     * @param contractTermValidationRule the ContractTermValidationRule entity
     * @return the ContractTermValidationRuleDTO
     */
    ContractTermValidationRuleDTO toDTO(ContractTermValidationRule contractTermValidationRule);

    /**
     * Convert ContractTermValidationRuleDTO to ContractTermValidationRule entity
     *
     * @param contractTermValidationRuleDTO the ContractTermValidationRuleDTO
     * @return the ContractTermValidationRule entity
     */
    ContractTermValidationRule toEntity(ContractTermValidationRuleDTO contractTermValidationRuleDTO);
}
