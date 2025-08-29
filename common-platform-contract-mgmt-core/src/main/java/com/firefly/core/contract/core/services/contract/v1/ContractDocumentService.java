package com.firefly.core.contract.core.services.contract.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contract.interfaces.dtos.contract.v1.ContractDocumentDTO;
import reactor.core.publisher.Mono;

public interface ContractDocumentService {

    /**
     * Retrieve a paginated list of contract documents for a specific contract ID.
     */
    Mono<PaginationResponse<ContractDocumentDTO>> getAllDocuments(Long contractId, PaginationRequest paginationRequest);

    /**
     * Create a new contract document for a specific contract ID.
     */
    Mono<ContractDocumentDTO> createDocument(Long contractId, ContractDocumentDTO dto);

    /**
     * Retrieve a specific contract document by contract ID and document ID.
     */
    Mono<ContractDocumentDTO> getDocument(Long contractId, Long documentId);

    /**
     * Update an existing contract document by contract ID and document ID.
     */
    Mono<ContractDocumentDTO> updateDocument(Long contractId, Long documentId, ContractDocumentDTO dto);

    /**
     * Delete a specific contract document by contract ID and document ID.
     */
    Mono<Void> deleteDocument(Long contractId, Long documentId);
}

