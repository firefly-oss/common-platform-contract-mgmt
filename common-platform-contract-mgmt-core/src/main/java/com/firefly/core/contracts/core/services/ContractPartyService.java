package com.firefly.core.contracts.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.interfaces.dtos.ContractPartyDTO;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Service interface for managing contract parties.
 */
public interface ContractPartyService {
    /**
     * Filters the contract parties based on the given criteria.
     *
     * @param filterRequest the request object containing filtering criteria for ContractPartyDTO
     * @return a reactive {@code Mono} emitting a {@code PaginationResponse} containing the filtered list of contract parties
     */
    Mono<PaginationResponse<ContractPartyDTO>> filterContractParties(FilterRequest<ContractPartyDTO> filterRequest);
    
    /**
     * Creates a new contract party based on the provided information.
     *
     * @param contractPartyDTO the DTO object containing details of the contract party to be created
     * @return a Mono that emits the created ContractPartyDTO object
     */
    Mono<ContractPartyDTO> createContractParty(ContractPartyDTO contractPartyDTO);
    
    /**
     * Updates an existing contract party with updated information.
     *
     * @param contractPartyId the unique identifier of the contract party to be updated
     * @param contractPartyDTO the data transfer object containing the updated details of the contract party
     * @return a reactive Mono containing the updated ContractPartyDTO
     */
    Mono<ContractPartyDTO> updateContractParty(UUID contractPartyId, ContractPartyDTO contractPartyDTO);
    
    /**
     * Deletes a contract party identified by its unique ID.
     *
     * @param contractPartyId the unique identifier of the contract party to be deleted
     * @return a Mono that completes when the contract party is successfully deleted or errors if the deletion fails
     */
    Mono<Void> deleteContractParty(UUID contractPartyId);
    
    /**
     * Retrieves a contract party by its unique identifier.
     *
     * @param contractPartyId the unique identifier of the contract party to retrieve
     * @return a Mono emitting the {@link ContractPartyDTO} representing the contract party if found,
     *         or an empty Mono if the contract party does not exist
     */
    Mono<ContractPartyDTO> getContractPartyById(UUID contractPartyId);
}
