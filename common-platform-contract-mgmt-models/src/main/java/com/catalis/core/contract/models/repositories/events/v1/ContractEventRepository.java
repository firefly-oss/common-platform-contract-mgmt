package com.catalis.core.contract.models.repositories.events.v1;

import com.catalis.core.contract.interfaces.enums.events.v1.EventTypeEnum;
import com.catalis.core.contract.models.entities.events.v1.ContractEvent;
import com.catalis.core.contract.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface ContractEventRepository extends BaseRepository<ContractEvent, Long> {

    Flux<ContractEvent> findByContractIdOrderByEventDateDesc(Long contractId, Pageable pageable);
    Mono<Long> countByContractId(Long contractId);

    Flux<ContractEvent> findByEventType(EventTypeEnum eventType, Pageable pageable);

    Flux<ContractEvent> findByEventDateBetween(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    );

    Mono<ContractEvent> findFirstByContractIdOrderByEventDateDesc(Long contractId);

}