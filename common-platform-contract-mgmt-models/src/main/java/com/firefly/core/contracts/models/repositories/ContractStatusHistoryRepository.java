package com.firefly.core.contracts.models.repositories;

import com.firefly.core.contracts.interfaces.enums.StatusCodeEnum;
import com.firefly.core.contracts.models.entities.ContractStatusHistory;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository interface for ContractStatusHistory entity operations
 */
@Repository
public interface ContractStatusHistoryRepository extends BaseRepository<ContractStatusHistory, Long> {

    /**
     * Find status history by contract ID
     */
    Flux<ContractStatusHistory> findByContractId(Long contractId);

    /**
     * Find status history by contract ID ordered by start date
     */
    Flux<ContractStatusHistory> findByContractIdOrderByStatusStartDateDesc(Long contractId);

    /**
     * Find status history by status code
     */
    Flux<ContractStatusHistory> findByStatusCode(StatusCodeEnum statusCode);

    /**
     * Find current status for a contract (no end date)
     */
    @Query("SELECT * FROM contract_status_history WHERE contract_id = :contractId AND status_end_date IS NULL")
    Mono<ContractStatusHistory> findCurrentStatusByContractId(@Param("contractId") Long contractId);

    /**
     * Find status history within a date range
     */
    @Query("SELECT * FROM contract_status_history WHERE status_start_date BETWEEN :fromDate AND :toDate")
    Flux<ContractStatusHistory> findByStatusStartDateBetween(@Param("fromDate") LocalDateTime fromDate, 
                                                             @Param("toDate") LocalDateTime toDate);

    /**
     * Find latest status history entry for a contract
     */
    @Query("SELECT * FROM contract_status_history WHERE contract_id = :contractId ORDER BY status_start_date DESC LIMIT 1")
    Mono<ContractStatusHistory> findLatestByContractId(@Param("contractId") Long contractId);

    /**
     * Find active status histories (no end date)
     */
    @Query("SELECT * FROM contract_status_history WHERE status_end_date IS NULL")
    Flux<ContractStatusHistory> findActiveStatusHistories();

    /**
     * Count status changes for a contract
     */
    Mono<Long> countByContractId(Long contractId);

    /**
     * Find contracts that had a specific status within a date range
     */
    @Query("SELECT * FROM contract_status_history WHERE status_code = :statusCode AND status_start_date BETWEEN :fromDate AND :toDate")
    Flux<ContractStatusHistory> findByStatusCodeAndDateRange(@Param("statusCode") StatusCodeEnum statusCode,
                                                             @Param("fromDate") LocalDateTime fromDate,
                                                             @Param("toDate") LocalDateTime toDate);
}
