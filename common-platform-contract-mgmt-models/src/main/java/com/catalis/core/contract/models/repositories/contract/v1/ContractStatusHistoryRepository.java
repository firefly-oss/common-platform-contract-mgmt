package com.catalis.core.contract.models.repositories.contract.v1;

import com.catalis.core.contract.interfaces.enums.contract.v1.StatusCodeEnum;
import com.catalis.core.contract.models.entities.contract.v1.ContractStatusHistory;
import com.catalis.core.contract.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface ContractStatusHistoryRepository extends BaseRepository<ContractStatusHistory, Long> {
    Flux<ContractStatusHistory> findByContractId(Long contractId);
    Flux<ContractStatusHistory> findByContractId(Long contractId, Pageable pageable);
    Mono<Long> countByContractId(Long contractId);
}
