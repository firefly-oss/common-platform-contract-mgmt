package com.firefly.core.contracts.models.repositories;

import com.firefly.core.contracts.models.entities.ContractParty;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository interface for ContractParty entity operations
 */
@Repository
public interface ContractPartyRepository extends BaseRepository<ContractParty, Long> {

    /**
     * Find contract parties by contract ID
     */
    Flux<ContractParty> findByContractId(Long contractId);

    /**
     * Find contract parties by party ID
     */
    Flux<ContractParty> findByPartyId(Long partyId);

    /**
     * Find active contract parties by contract ID
     */
    Flux<ContractParty> findByContractIdAndIsActive(Long contractId, Boolean isActive);

    /**
     * Find active contract parties by party ID
     */
    Flux<ContractParty> findByPartyIdAndIsActive(Long partyId, Boolean isActive);

    /**
     * Find contract parties by role
     */
    Flux<ContractParty> findByRoleInContractId(Long roleInContractId);

    /**
     * Find contract party by contract and party ID
     */
    Mono<ContractParty> findByContractIdAndPartyId(Long contractId, Long partyId);

    /**
     * Count active parties for a contract
     */
    @Query("SELECT COUNT(*) FROM contract_party WHERE contract_id = :contractId AND is_active = true")
    Mono<Long> countActivePartiesByContractId(@Param("contractId") Long contractId);

    /**
     * Count contracts for a party
     */
    Mono<Long> countByPartyIdAndIsActive(Long partyId, Boolean isActive);
}
