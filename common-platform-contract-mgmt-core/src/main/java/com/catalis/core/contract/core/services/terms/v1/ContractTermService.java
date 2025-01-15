package com.catalis.core.contract.core.services.terms.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.interfaces.dtos.terms.v1.ContractTermDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing contract terms.
 */
public interface ContractTermService {

    /**
     * Retrieves a paginated list of terms associated with a specific contract.
     */
    Mono<PaginationResponse<ContractTermDTO>> getAllTerms(Long contractId, PaginationRequest paginationRequest);

    /**
     * Creates a new term for the specified contract.
     */
    Mono<ContractTermDTO> createTerm(Long contractId, ContractTermDTO dto);

    /**
     * Retrieves a specific term by contract ID and term ID.
     */
    Mono<ContractTermDTO> getTerm(Long contractId, Long termId);

    /**
     * Updates an existing contract term by contract ID and term ID.
     */
    Mono<ContractTermDTO> updateTerm(Long contractId, Long termId, ContractTermDTO dto);

    /**
     * Deletes a specific contract term by contract ID and term ID.
     */
    Mono<Void> deleteTerm(Long contractId, Long termId);
}
