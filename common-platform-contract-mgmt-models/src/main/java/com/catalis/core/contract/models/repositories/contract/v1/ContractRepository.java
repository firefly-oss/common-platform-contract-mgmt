package com.catalis.core.contract.models.repositories.contract.v1;

import com.catalis.core.contract.interfaces.enums.contract.v1.ContractStatusEnum;
import com.catalis.core.contract.interfaces.enums.contract.v1.ContractTypeEnum;
import com.catalis.core.contract.models.entities.contract.v1.Contract;
import com.catalis.core.contract.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface ContractRepository extends BaseRepository<Contract, Long> {

    Mono<Contract> findByContractNumber(String contractNumber);

    Flux<Contract> findByContractType(ContractTypeEnum type, Pageable pageable);

    Flux<Contract> findByContractStatus(ContractStatusEnum status, Pageable pageable);

    Flux<Contract> findByProductId(Long productId, Pageable pageable);
    Mono<Long> countByProductId(Long productId);

    Mono<Long> countByContractType(ContractTypeEnum type);

    Flux<Contract> findByContractNumberLikeAndContractTypeAndContractStatus(
            String contractNumber,
            ContractTypeEnum contractType,
            ContractStatusEnum contractStatus,
            Pageable pageable
    );

    Flux<Contract> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    Flux<Contract> findByContractStatusAndStartDateGreaterThan(
            ContractStatusEnum status,
            LocalDateTime date,
            Pageable pageable
    );

}
