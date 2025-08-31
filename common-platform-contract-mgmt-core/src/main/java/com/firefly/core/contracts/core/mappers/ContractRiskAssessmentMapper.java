package com.firefly.core.contracts.core.mappers;

import com.firefly.core.contracts.interfaces.dtos.ContractRiskAssessmentDTO;
import com.firefly.core.contracts.models.entities.ContractRiskAssessment;
import org.mapstruct.Mapper;

/**
 * MapStruct mapper for ContractRiskAssessment entity and ContractRiskAssessmentDTO
 */
@Mapper(componentModel = "spring")
public interface ContractRiskAssessmentMapper {

    /**
     * Convert ContractRiskAssessment entity to ContractRiskAssessmentDTO
     *
     * @param contractRiskAssessment the ContractRiskAssessment entity
     * @return the ContractRiskAssessmentDTO
     */
    ContractRiskAssessmentDTO toDTO(ContractRiskAssessment contractRiskAssessment);

    /**
     * Convert ContractRiskAssessmentDTO to ContractRiskAssessment entity
     *
     * @param contractRiskAssessmentDTO the ContractRiskAssessmentDTO
     * @return the ContractRiskAssessment entity
     */
    ContractRiskAssessment toEntity(ContractRiskAssessmentDTO contractRiskAssessmentDTO);
}
