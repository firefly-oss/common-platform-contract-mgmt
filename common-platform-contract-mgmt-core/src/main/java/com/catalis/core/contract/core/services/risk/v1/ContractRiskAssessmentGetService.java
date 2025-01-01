package com.catalis.core.contract.core.services.risk.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.contract.core.mappers.risk.v1.ContractRiskAssessmentMapper;
import com.catalis.core.contract.interfaces.dtos.risk.v1.ContractRiskAssessmentDTO;
import com.catalis.core.contract.models.repositories.risk.v1.ContractRiskAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class ContractRiskAssessmentGetService {

    @Autowired
    private ContractRiskAssessmentRepository repository;

    @Autowired
    private ContractRiskAssessmentMapper mapper;

    /**
     * Retrieves a contract risk assessment using the provided unique identifier.
     *
     * This method searches for a contract risk assessment in the repository by its ID.
     * If found, it maps the entity to a data transfer object (DTO); otherwise, it throws an error.
     * Any exceptions encountered during the process are wrapped in a runtime exception.
     *
     * @param contractRiskAssessmentId the unique identifier of the contract risk assessment to be retrieved
     * @return a reactive Mono containing the retrieved ContractRiskAssessmentDTO, or an error if not found
     */
    public Mono<ContractRiskAssessmentDTO> getContractRiskAssessment(Long contractRiskAssessmentId) {
        return repository.findById(contractRiskAssessmentId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract risk assessment with ID " + contractRiskAssessmentId + " not found")))
                .map(mapper::toDto)
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to retrieve contract risk assessment: " + error.getMessage(), error)));
    }

    /**
     * Retrieves a paginated list of contract risk assessments for a specific contract ID,
     * ordered by the assessment date in descending order.
     *
     * This method utilizes pagination utilities to facilitate paginated queries and maps
     * the resulting entities to their corresponding DTOs.
     *
     * @param contractId the unique identifier of the contract whose risk assessments are to be retrieved
     * @param paginationRequest the pagination criteria for fetching the results, such as page number and size
     * @return a reactive Mono containing a PaginationResponse with a list of ContractRiskAssessmentDTO objects
     */
    public Mono<PaginationResponse<ContractRiskAssessmentDTO>> findByContractIdOrderByAssessmentDateDesc(
            Long contractId, PaginationRequest paginationRequest) {

        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDto,
                pageable -> repository.findByContractIdOrderByAssessmentDateDesc(contractId, pageable),
                () -> repository.countByContractId(contractId)
        );

    }



}
