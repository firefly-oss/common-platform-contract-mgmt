package com.firefly.core.contracts.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.interfaces.dtos.ContractTermDynamicDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing contract term dynamics.
 */
public interface ContractTermDynamicService {
    /**
     * Filters the contract term dynamics based on the given criteria.
     *
     * @param filterRequest the request object containing filtering criteria for ContractTermDynamicDTO
     * @return a reactive {@code Mono} emitting a {@code PaginationResponse} containing the filtered list of contract term dynamics
     */
    Mono<PaginationResponse<ContractTermDynamicDTO>> filterContractTermDynamics(FilterRequest<ContractTermDynamicDTO> filterRequest);
    
    /**
     * Creates a new contract term dynamic based on the provided information.
     *
     * @param contractTermDynamicDTO the DTO object containing details of the contract term dynamic to be created
     * @return a Mono that emits the created ContractTermDynamicDTO object
     */
    Mono<ContractTermDynamicDTO> createContractTermDynamic(ContractTermDynamicDTO contractTermDynamicDTO);
    
    /**
     * Updates an existing contract term dynamic with updated information.
     *
     * @param termId the unique identifier of the contract term dynamic to be updated
     * @param contractTermDynamicDTO the data transfer object containing the updated details of the contract term dynamic
     * @return a reactive Mono containing the updated ContractTermDynamicDTO
     */
    Mono<ContractTermDynamicDTO> updateContractTermDynamic(Long termId, ContractTermDynamicDTO contractTermDynamicDTO);
    
    /**
     * Deletes a contract term dynamic identified by its unique ID.
     *
     * @param termId the unique identifier of the contract term dynamic to be deleted
     * @return a Mono that completes when the contract term dynamic is successfully deleted or errors if the deletion fails
     */
    Mono<Void> deleteContractTermDynamic(Long termId);
    
    /**
     * Retrieves a contract term dynamic by its unique identifier.
     *
     * @param termId the unique identifier of the contract term dynamic to retrieve
     * @return a Mono emitting the {@link ContractTermDynamicDTO} representing the contract term dynamic if found,
     *         or an empty Mono if the contract term dynamic does not exist
     */
    Mono<ContractTermDynamicDTO> getContractTermDynamicById(Long termId);
}
