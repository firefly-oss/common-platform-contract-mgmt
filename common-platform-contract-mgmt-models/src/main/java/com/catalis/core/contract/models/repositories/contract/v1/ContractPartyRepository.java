package com.catalis.core.contract.models.repositories.contract.v1;

import com.catalis.core.contract.models.entities.contract.v1.ContractParty;
import com.catalis.core.contract.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ContractPartyRepository extends BaseRepository<ContractParty, Long> {
    Flux<ContractParty> findByContractId(Long contractId);
    Flux<ContractParty> findByContractId(Long contractId, Pageable pageable);
    Mono<Long> countByContractId(Long contractId);
}
