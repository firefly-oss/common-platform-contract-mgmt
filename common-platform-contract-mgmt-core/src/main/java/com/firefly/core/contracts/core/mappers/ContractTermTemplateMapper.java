package com.firefly.core.contracts.core.mappers;

import com.firefly.core.contracts.interfaces.dtos.ContractTermTemplateDTO;
import com.firefly.core.contracts.models.entities.ContractTermTemplate;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper for ContractTermTemplate entity and ContractTermTemplateDTO
 */
@Mapper(componentModel = "spring")
public interface ContractTermTemplateMapper {

    /**
     * Convert ContractTermTemplate entity to ContractTermTemplateDTO
     *
     * @param contractTermTemplate the ContractTermTemplate entity
     * @return the ContractTermTemplateDTO
     */
    ContractTermTemplateDTO toDTO(ContractTermTemplate contractTermTemplate);

    /**
     * Convert ContractTermTemplateDTO to ContractTermTemplate entity
     *
     * @param contractTermTemplateDTO the ContractTermTemplateDTO
     * @return the ContractTermTemplate entity
     */
    ContractTermTemplate toEntity(ContractTermTemplateDTO contractTermTemplateDTO);
}
