package com.firefly.core.contracts.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.interfaces.dtos.ContractRiskAssessmentDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing contract risk assessments.
 */
public interface ContractRiskAssessmentService {
    /**
     * Filters the contract risk assessments based on the given criteria.
     *
     * @param filterRequest the request object containing filtering criteria for ContractRiskAssessmentDTO
     * @return a reactive {@code Mono} emitting a {@code PaginationResponse} containing the filtered list of contract risk assessments
     */
    Mono<PaginationResponse<ContractRiskAssessmentDTO>> filterContractRiskAssessments(FilterRequest<ContractRiskAssessmentDTO> filterRequest);
    
    /**
     * Creates a new contract risk assessment based on the provided information.
     *
     * @param contractRiskAssessmentDTO the DTO object containing details of the contract risk assessment to be created
     * @return a Mono that emits the created ContractRiskAssessmentDTO object
     */
    Mono<ContractRiskAssessmentDTO> createContractRiskAssessment(ContractRiskAssessmentDTO contractRiskAssessmentDTO);
    
    /**
     * Updates an existing contract risk assessment with updated information.
     *
     * @param contractRiskAssessmentId the unique identifier of the contract risk assessment to be updated
     * @param contractRiskAssessmentDTO the data transfer object containing the updated details of the contract risk assessment
     * @return a reactive Mono containing the updated ContractRiskAssessmentDTO
     */
    Mono<ContractRiskAssessmentDTO> updateContractRiskAssessment(Long contractRiskAssessmentId, ContractRiskAssessmentDTO contractRiskAssessmentDTO);
    
    /**
     * Deletes a contract risk assessment identified by its unique ID.
     *
     * @param contractRiskAssessmentId the unique identifier of the contract risk assessment to be deleted
     * @return a Mono that completes when the contract risk assessment is successfully deleted or errors if the deletion fails
     */
    Mono<Void> deleteContractRiskAssessment(Long contractRiskAssessmentId);
    
    /**
     * Retrieves a contract risk assessment by its unique identifier.
     *
     * @param contractRiskAssessmentId the unique identifier of the contract risk assessment to retrieve
     * @return a Mono emitting the {@link ContractRiskAssessmentDTO} representing the contract risk assessment if found,
     *         or an empty Mono if the contract risk assessment does not exist
     */
    Mono<ContractRiskAssessmentDTO> getContractRiskAssessmentById(Long contractRiskAssessmentId);
}
