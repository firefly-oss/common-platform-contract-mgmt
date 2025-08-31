package com.firefly.core.contracts.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.interfaces.dtos.ContractEventDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing contract events.
 */
public interface ContractEventService {
    /**
     * Filters the contract events based on the given criteria.
     *
     * @param filterRequest the request object containing filtering criteria for ContractEventDTO
     * @return a reactive {@code Mono} emitting a {@code PaginationResponse} containing the filtered list of contract events
     */
    Mono<PaginationResponse<ContractEventDTO>> filterContractEvents(FilterRequest<ContractEventDTO> filterRequest);
    
    /**
     * Creates a new contract event based on the provided information.
     *
     * @param contractEventDTO the DTO object containing details of the contract event to be created
     * @return a Mono that emits the created ContractEventDTO object
     */
    Mono<ContractEventDTO> createContractEvent(ContractEventDTO contractEventDTO);
    
    /**
     * Updates an existing contract event with updated information.
     *
     * @param contractEventId the unique identifier of the contract event to be updated
     * @param contractEventDTO the data transfer object containing the updated details of the contract event
     * @return a reactive Mono containing the updated ContractEventDTO
     */
    Mono<ContractEventDTO> updateContractEvent(Long contractEventId, ContractEventDTO contractEventDTO);
    
    /**
     * Deletes a contract event identified by its unique ID.
     *
     * @param contractEventId the unique identifier of the contract event to be deleted
     * @return a Mono that completes when the contract event is successfully deleted or errors if the deletion fails
     */
    Mono<Void> deleteContractEvent(Long contractEventId);
    
    /**
     * Retrieves a contract event by its unique identifier.
     *
     * @param contractEventId the unique identifier of the contract event to retrieve
     * @return a Mono emitting the {@link ContractEventDTO} representing the contract event if found,
     *         or an empty Mono if the contract event does not exist
     */
    Mono<ContractEventDTO> getContractEventById(Long contractEventId);
}
