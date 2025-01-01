package com.catalis.core.contract.core.mappers.risk.v1;

import com.catalis.core.contract.interfaces.dtos.risk.v1.ContractRiskAssessmentDTO;
import com.catalis.core.contract.models.entities.risk.v1.ContractRiskAssessment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractRiskAssessmentMapper {
    ContractRiskAssessmentDTO toDto(ContractRiskAssessment entity);
    ContractRiskAssessment toEntity(ContractRiskAssessmentDTO dto);
}
