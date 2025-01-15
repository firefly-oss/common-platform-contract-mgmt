package com.catalis.core.contract.core.services.risk.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.interfaces.dtos.risk.v1.ContractRiskAssessmentDTO;
import reactor.core.publisher.Mono;

public interface ContractRiskAssessmentService {

    /**
     * Retrieves a paginated list of risk assessments for a specific contract.
     */
    Mono<PaginationResponse<ContractRiskAssessmentDTO>> getAllRiskAssessments(Long contractId,
                                                                              PaginationRequest paginationRequest);

    /**
     * Creates a new risk assessment record for the specified contract.
     */
    Mono<ContractRiskAssessmentDTO> createRiskAssessment(Long contractId, ContractRiskAssessmentDTO dto);

    /**
     * Retrieves a specific risk assessment by contract ID and risk ID.
     */
    Mono<ContractRiskAssessmentDTO> getRiskAssessment(Long contractId, Long riskId);

    /**
     * Updates an existing risk assessment by contract ID and risk ID.
     */
    Mono<ContractRiskAssessmentDTO> updateRiskAssessment(Long contractId, Long riskId, ContractRiskAssessmentDTO dto);

    /**
     * Deletes a specific risk assessment record by contract ID and risk ID.
     */
    Mono<Void> deleteRiskAssessment(Long contractId, Long riskId);
}