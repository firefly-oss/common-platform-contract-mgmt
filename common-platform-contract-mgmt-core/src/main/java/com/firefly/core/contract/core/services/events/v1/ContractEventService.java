package com.firefly.core.contract.core.services.events.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contract.interfaces.dtos.events.v1.ContractEventDTO;
import reactor.core.publisher.Mono;

public interface ContractEventService {

    /**
     * Retrieves a paginated list of contract events for a specific contract ID.
     */
    Mono<PaginationResponse<ContractEventDTO>> getAllEvents(Long contractId, PaginationRequest paginationRequest);

    /**
     * Creates a new event record for the specified contract.
     */
    Mono<ContractEventDTO> createEvent(Long contractId, ContractEventDTO dto);

    /**
     * Retrieves a specific contract event by contract ID and event ID.
     */
    Mono<ContractEventDTO> getEvent(Long contractId, Long eventId);

    /**
     * Updates an existing contract event by contract ID and event ID.
     */
    Mono<ContractEventDTO> updateEvent(Long contractId, Long eventId, ContractEventDTO dto);

    /**
     * Deletes a specific contract event by contract ID and event ID.
     */
    Mono<Void> deleteEvent(Long contractId, Long eventId);
}
