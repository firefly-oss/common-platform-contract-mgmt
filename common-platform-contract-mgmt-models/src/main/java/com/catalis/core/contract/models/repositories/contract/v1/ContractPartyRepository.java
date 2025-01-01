package com.catalis.core.contract.models.repositories.contract.v1;

import com.catalis.core.contract.interfaces.enums.contract.v1.RoleInContractEnum;
import com.catalis.core.contract.models.entities.contract.v1.ContractParty;
import com.catalis.core.contract.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface ContractPartyRepository extends BaseRepository<ContractParty, Long> {

    Flux<ContractParty> findByContractId(Long contractId, Pageable pageable);

    Flux<ContractParty> findByPartyId(Long partyId, Pageable pageable);

    Flux<ContractParty> findByContractIdAndRoleInContract(Long contractId, RoleInContractEnum role, Pageable pageable);

    Flux<ContractParty> findByActiveTrue(Pageable pageable);

    Mono<Long> countByContractId(Long contractId);

    Flux<ContractParty> findByContractIdAndDateJoinedBetween(
            Long contractId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    );

}
