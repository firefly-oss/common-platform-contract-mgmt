package com.firefly.core.contract.models.repositories.contract.v1;

import com.firefly.core.contract.models.entities.contract.v1.ContractStatusHistory;
import com.firefly.core.contract.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContractStatusHistoryRepository extends BaseRepository<ContractStatusHistory, Long> {
    Flux<ContractStatusHistory> findByContractId(Long contractId);
    Flux<ContractStatusHistory> findByContractId(Long contractId, Pageable pageable);
    Mono<Long> countByContractId(Long contractId);
}
