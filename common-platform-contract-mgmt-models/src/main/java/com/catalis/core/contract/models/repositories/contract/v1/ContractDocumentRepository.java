package com.catalis.core.contract.models.repositories.contract.v1;

import com.catalis.core.contract.models.entities.contract.v1.ContractDocument;
import com.catalis.core.contract.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface ContractDocumentRepository extends BaseRepository<ContractDocument, Long> {

    Flux<ContractDocument> findByContractId(Long contractId, Pageable pageable);
    Mono<Long> countByContractId(Long contractId);

    Flux<ContractDocument> findByDocumentType(String documentType, Pageable pageable);

    Flux<ContractDocument> findByDateAddedBetween(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    );
}
