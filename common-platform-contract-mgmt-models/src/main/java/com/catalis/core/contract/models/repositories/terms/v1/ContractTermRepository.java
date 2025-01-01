package com.catalis.core.contract.models.repositories.terms.v1;

import com.catalis.core.contract.interfaces.enums.terms.v1.TermTypeEnum;
import com.catalis.core.contract.models.entities.terms.v1.ContractTerm;
import com.catalis.core.contract.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface ContractTermRepository extends BaseRepository<ContractTerm, Long> {

    Flux<ContractTerm> findByContractId(Long contractId, Pageable pageable);
    Mono<Long> countByContractId(Long contractId);

    Flux<ContractTerm> findByTermType(TermTypeEnum termType, Pageable pageable);

    Flux<ContractTerm> findByContractIdAndEffectiveDateLessThanEqualAndExpirationDateGreaterThanEqual(
            Long contractId,
            LocalDateTime effectiveDate,
            LocalDateTime expirationDate,
            Pageable pageable
    );

    Flux<ContractTerm> findByContractIdAndEffectiveDateLessThanEqualAndExpirationDateIsNull(
            Long contractId,
            LocalDateTime effectiveDate,
            Pageable pageable
    );

}
