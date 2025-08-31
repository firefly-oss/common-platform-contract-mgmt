package com.firefly.core.contracts.models.repositories;

import com.firefly.core.contracts.interfaces.enums.EventTypeEnum;
import com.firefly.core.contracts.models.entities.ContractEvent;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository interface for ContractEvent entity operations
 */
@Repository
public interface ContractEventRepository extends BaseRepository<ContractEvent, Long> {

    /**
     * Find events by contract ID
     */
    Flux<ContractEvent> findByContractId(Long contractId);

    /**
     * Find events by contract ID ordered by event date
     */
    Flux<ContractEvent> findByContractIdOrderByEventDateDesc(Long contractId);

    /**
     * Find events by event type
     */
    Flux<ContractEvent> findByEventType(EventTypeEnum eventType);

    /**
     * Find events by contract ID and event type
     */
    Flux<ContractEvent> findByContractIdAndEventType(Long contractId, EventTypeEnum eventType);

    /**
     * Find events within a date range
     */
    Flux<ContractEvent> findByEventDateBetween(LocalDateTime fromDate, LocalDateTime toDate);

    /**
     * Find events by contract ID within a date range
     */
    @Query("SELECT * FROM contract_event WHERE contract_id = :contractId AND event_date BETWEEN :fromDate AND :toDate ORDER BY event_date DESC")
    Flux<ContractEvent> findByContractIdAndEventDateBetween(@Param("contractId") Long contractId,
                                                            @Param("fromDate") LocalDateTime fromDate,
                                                            @Param("toDate") LocalDateTime toDate);

    /**
     * Find recent events for a contract
     */
    @Query("SELECT * FROM contract_event WHERE contract_id = :contractId ORDER BY event_date DESC LIMIT :limit")
    Flux<ContractEvent> findRecentEventsByContractId(@Param("contractId") Long contractId, 
                                                     @Param("limit") Integer limit);

    /**
     * Find events after a specific date
     */
    Flux<ContractEvent> findByEventDateAfter(LocalDateTime eventDate);

    /**
     * Count events by contract ID
     */
    Mono<Long> countByContractId(Long contractId);

    /**
     * Count events by event type
     */
    Mono<Long> countByEventType(EventTypeEnum eventType);

    /**
     * Find latest event for a contract
     */
    @Query("SELECT * FROM contract_event WHERE contract_id = :contractId ORDER BY event_date DESC LIMIT 1")
    Mono<ContractEvent> findLatestEventByContractId(@Param("contractId") Long contractId);
}
