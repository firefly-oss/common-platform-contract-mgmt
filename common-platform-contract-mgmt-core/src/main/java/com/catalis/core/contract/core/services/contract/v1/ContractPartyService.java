package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractPartyDTO;
import reactor.core.publisher.Mono;

public interface ContractPartyService {

    /**
     * Retrieves a paginated list of parties associated with a specific contract.
     */
    Mono<PaginationResponse<ContractPartyDTO>> getAllParties(Long contractId, PaginationRequest paginationRequest);

    /**
     * Creates a new party link for the specified contract.
     */
    Mono<ContractPartyDTO> createPartyLink(Long contractId, ContractPartyDTO dto);

    /**
     * Retrieves a specific contract party by contract ID and party ID.
     */
    Mono<ContractPartyDTO> getParty(Long contractId, Long partyId);

    /**
     * Updates an existing contract party link by contract ID and party ID.
     */
    Mono<ContractPartyDTO> updateParty(Long contractId, Long partyId, ContractPartyDTO dto);

    /**
     * Deletes a specific contract party link by contract ID and party ID.
     */
    Mono<Void> deleteParty(Long contractId, Long partyId);
}
