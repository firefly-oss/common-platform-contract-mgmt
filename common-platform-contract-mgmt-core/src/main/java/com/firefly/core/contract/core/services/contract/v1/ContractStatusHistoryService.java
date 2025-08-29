package com.firefly.core.contract.core.services.contract.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contract.interfaces.dtos.contract.v1.ContractStatusHistoryDTO;
import reactor.core.publisher.Mono;

public interface ContractStatusHistoryService {

    /**
     * Retrieves a paginated list of status history records for a specific contract.
     */
    Mono<PaginationResponse<ContractStatusHistoryDTO>> getAllStatuses(Long contractId, PaginationRequest paginationRequest);

    /**
     * Creates a new contract status history record.
     */
    Mono<ContractStatusHistoryDTO> createStatusHistory(Long contractId, ContractStatusHistoryDTO dto);

    /**
     * Retrieves a specific contract status history record by contract ID and history ID.
     */
    Mono<ContractStatusHistoryDTO> getStatusHistory(Long contractId, Long historyId);

    /**
     * Updates an existing contract status history record by contract ID and history ID.
     */
    Mono<ContractStatusHistoryDTO> updateStatusHistory(Long contractId, Long historyId, ContractStatusHistoryDTO dto);

    /**
     * Deletes a specific contract status history record by contract ID and history ID.
     */
    Mono<Void> deleteStatusHistory(Long contractId, Long historyId);
}
