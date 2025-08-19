package com.catalis.core.contract.models.repositories.risk.v1;

import com.catalis.core.contract.models.entities.risk.v1.ContractRiskAssessment;
import com.catalis.core.contract.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContractRiskAssessmentRepository extends BaseRepository<ContractRiskAssessment, Long> {
    Flux<ContractRiskAssessment> findByContractId(Long contractId);
    Flux<ContractRiskAssessment> findByContractId(Long contractId, Pageable pageable);
    Mono<Long> countByContractId(Long contractId);
}
