package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractDTO;
import reactor.core.publisher.Mono;

public interface ContractService {

    /**
     * Filter contracts based on filter criteria
     */
    Mono<PaginationResponse<ContractDTO>> filterContracts(FilterRequest<ContractDTO> filterRequest);

    /**
     * Create a new contract
     */
    Mono<ContractDTO> createContract(ContractDTO dto);

    /**
     * Retrieve a contract by its ID
     */
    Mono<ContractDTO> getContract(Long contractId);

    /**
     * Update an existing contract by its ID
     */
    Mono<ContractDTO> updateContract(Long contractId, ContractDTO dto);

    /**
     * Delete a contract by its ID
     */
    Mono<Void> deleteContract(Long contractId);

}
