package com.firefly.core.contracts.models.repositories;

import com.firefly.core.contracts.models.entities.ContractDocument;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository interface for ContractDocument entity operations
 */
@Repository
public interface ContractDocumentRepository extends BaseRepository<ContractDocument, Long> {

    /**
     * Find documents by contract ID
     */
    Flux<ContractDocument> findByContractId(Long contractId);

    /**
     * Find documents by document type ID
     */
    Flux<ContractDocument> findByDocumentTypeId(Long documentTypeId);

    /**
     * Find documents by contract ID and document type ID
     */
    Flux<ContractDocument> findByContractIdAndDocumentTypeId(Long contractId, Long documentTypeId);

    /**
     * Find document by document ID
     */
    Mono<ContractDocument> findByDocumentId(Long documentId);

    /**
     * Find documents added after a specific date
     */
    Flux<ContractDocument> findByDateAddedAfter(LocalDateTime dateAdded);

    /**
     * Find documents added within a date range
     */
    @Query("SELECT * FROM contract_document WHERE date_added BETWEEN :fromDate AND :toDate")
    Flux<ContractDocument> findByDateAddedBetween(@Param("fromDate") LocalDateTime fromDate, 
                                                  @Param("toDate") LocalDateTime toDate);

    /**
     * Count documents by contract ID
     */
    Mono<Long> countByContractId(Long contractId);

    /**
     * Count documents by document type ID
     */
    Mono<Long> countByDocumentTypeId(Long documentTypeId);

    /**
     * Find latest documents for a contract
     */
    @Query("SELECT * FROM contract_document WHERE contract_id = :contractId ORDER BY date_added DESC LIMIT :limit")
    Flux<ContractDocument> findLatestByContractId(@Param("contractId") Long contractId, 
                                                  @Param("limit") Integer limit);
}
