package com.catalis.core.contract.models.repositories.risk.v1;

import com.catalis.core.contract.interfaces.enums.risk.v1.RiskLevelEnum;
import com.catalis.core.contract.models.entities.risk.v1.ContractRiskAssessment;
import com.catalis.core.contract.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ContractRiskAssessmentRepository extends BaseRepository<ContractRiskAssessment, Long> {

    Flux<ContractRiskAssessment> findByContractIdOrderByAssessmentDateDesc(Long contractId, Pageable pageable);
    Mono<Long> countByContractId(Long contractId);

    Flux<ContractRiskAssessment> findByRiskLevel(RiskLevelEnum riskLevel, Pageable pageable);

    Flux<ContractRiskAssessment> findByRiskScoreGreaterThanEqual(BigDecimal minimumScore, Pageable pageable);

    Mono<ContractRiskAssessment> findFirstByContractIdOrderByAssessmentDateDesc(Long contractId);

    Flux<ContractRiskAssessment> findByAssessmentDateBetweenAndRiskLevel(
            LocalDateTime startDate,
            LocalDateTime endDate,
            RiskLevelEnum riskLevel,
            Pageable pageable
    );

}
