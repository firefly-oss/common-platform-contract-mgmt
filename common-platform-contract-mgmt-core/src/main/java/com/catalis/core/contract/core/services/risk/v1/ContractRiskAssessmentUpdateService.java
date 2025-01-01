package com.catalis.core.contract.core.services.risk.v1;

import com.catalis.core.contract.core.mappers.risk.v1.ContractRiskAssessmentMapper;
import com.catalis.core.contract.interfaces.dtos.risk.v1.ContractRiskAssessmentDTO;
import com.catalis.core.contract.models.entities.risk.v1.ContractRiskAssessment;
import com.catalis.core.contract.models.repositories.risk.v1.ContractRiskAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractRiskAssessmentUpdateService {

    @Autowired
    private ContractRiskAssessmentRepository repository;

    @Autowired
    private ContractRiskAssessmentMapper mapper;

    /**
     * Updates an existing Contract Risk Assessment with the provided details. If a field in the
     * request is null, the corresponding field from the existing entity is retained.
     *
     * @param id The unique identifier of the Contract Risk Assessment to be updated.
     * @param request A DTO containing the new details for the Contract Risk Assessment.
     * @return A Mono emitting the updated ContractRiskAssessmentDTO, or an error if the update fails
     *         or the entity with the specified ID does not exist.
     */
    public Mono<ContractRiskAssessmentDTO> updateContractRiskAssessment(Long id, ContractRiskAssessmentDTO request) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract risk assessment with ID " + id + " not found")))
                .flatMap(existingEntity -> {
                    ContractRiskAssessment updatedEntity = mapper.toEntity(request);
                    updatedEntity.setContractId(existingEntity.getContractId());
                    updatedEntity.setAssessmentDate(request.getAssessmentDate() != null ? request.getAssessmentDate() : existingEntity.getAssessmentDate());
                    updatedEntity.setRiskScore(request.getRiskScore() != null ? request.getRiskScore() : existingEntity.getRiskScore());
                    updatedEntity.setRiskLevel(request.getRiskLevel() != null ? request.getRiskLevel() : existingEntity.getRiskLevel());
                    updatedEntity.setAssessor(request.getAssessor() != null ? request.getAssessor() : existingEntity.getAssessor());
                    updatedEntity.setNotes(request.getNotes() != null ? request.getNotes() : existingEntity.getNotes());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDto)
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to update contract risk assessment: " + error.getMessage(), error)));
    }

}
