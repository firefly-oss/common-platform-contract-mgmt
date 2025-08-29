package com.firefly.core.contract.models.repositories.events.v1;

import com.firefly.core.contract.models.entities.events.v1.ContractEvent;
import com.firefly.core.contract.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContractEventRepository extends BaseRepository<ContractEvent, Long> {
    Flux<ContractEvent> findByContractId(Long contractId, Pageable pageable);
    Mono<Long> countByContractId(Long contractId);
}