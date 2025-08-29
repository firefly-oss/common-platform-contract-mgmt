package com.firefly.core.contract.models.repositories.terms.v1;

import com.firefly.core.contract.models.entities.terms.v1.ContractTerm;
import com.firefly.core.contract.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContractTermRepository extends BaseRepository<ContractTerm, Long> {
    Flux<ContractTerm> findByContractId(Long contractId, Pageable pageable);
    Mono<Long> countByContractId(Long contractId);
}