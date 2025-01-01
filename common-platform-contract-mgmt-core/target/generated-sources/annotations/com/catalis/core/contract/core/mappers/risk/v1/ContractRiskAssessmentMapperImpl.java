package com.catalis.core.contract.core.mappers.risk.v1;

import com.catalis.core.contract.interfaces.dtos.risk.v1.ContractRiskAssessmentDTO;
import com.catalis.core.contract.models.entities.risk.v1.ContractRiskAssessment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-01T18:18:41+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class ContractRiskAssessmentMapperImpl implements ContractRiskAssessmentMapper {

    @Override
    public ContractRiskAssessmentDTO toDto(ContractRiskAssessment entity) {
        if ( entity == null ) {
            return null;
        }

        ContractRiskAssessmentDTO.ContractRiskAssessmentDTOBuilder<?, ?> contractRiskAssessmentDTO = ContractRiskAssessmentDTO.builder();

        contractRiskAssessmentDTO.dateCreated( entity.getDateCreated() );
        contractRiskAssessmentDTO.dateUpdated( entity.getDateUpdated() );
        contractRiskAssessmentDTO.contractRiskAssessmentId( entity.getContractRiskAssessmentId() );
        contractRiskAssessmentDTO.contractId( entity.getContractId() );
        contractRiskAssessmentDTO.riskScore( entity.getRiskScore() );
        contractRiskAssessmentDTO.riskLevel( entity.getRiskLevel() );
        contractRiskAssessmentDTO.assessmentDate( entity.getAssessmentDate() );
        contractRiskAssessmentDTO.assessor( entity.getAssessor() );
        contractRiskAssessmentDTO.notes( entity.getNotes() );

        return contractRiskAssessmentDTO.build();
    }

    @Override
    public ContractRiskAssessment toEntity(ContractRiskAssessmentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ContractRiskAssessment contractRiskAssessment = new ContractRiskAssessment();

        contractRiskAssessment.setDateCreated( dto.getDateCreated() );
        contractRiskAssessment.setDateUpdated( dto.getDateUpdated() );
        contractRiskAssessment.setContractRiskAssessmentId( dto.getContractRiskAssessmentId() );
        contractRiskAssessment.setContractId( dto.getContractId() );
        contractRiskAssessment.setRiskScore( dto.getRiskScore() );
        contractRiskAssessment.setRiskLevel( dto.getRiskLevel() );
        contractRiskAssessment.setAssessmentDate( dto.getAssessmentDate() );
        contractRiskAssessment.setAssessor( dto.getAssessor() );
        contractRiskAssessment.setNotes( dto.getNotes() );

        return contractRiskAssessment;
    }
}
