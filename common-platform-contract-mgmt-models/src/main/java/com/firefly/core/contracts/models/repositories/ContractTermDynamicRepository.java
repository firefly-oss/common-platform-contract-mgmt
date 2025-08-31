package com.firefly.core.contracts.models.repositories;

import com.firefly.core.contracts.models.entities.ContractTermDynamic;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Repository interface for ContractTermDynamic entity operations
 */
@Repository
public interface ContractTermDynamicRepository extends BaseRepository<ContractTermDynamic, Long> {

    /**
     * Find dynamic terms by contract ID
     */
    Flux<ContractTermDynamic> findByContractId(Long contractId);

    /**
     * Find dynamic terms by term template ID
     */
    Flux<ContractTermDynamic> findByTermTemplateId(Long termTemplateId);

    /**
     * Find active dynamic terms by contract ID
     */
    Flux<ContractTermDynamic> findByContractIdAndIsActive(Long contractId, Boolean isActive);

    /**
     * Find dynamic terms by contract ID and term template ID
     */
    Flux<ContractTermDynamic> findByContractIdAndTermTemplateId(Long contractId, Long termTemplateId);

    /**
     * Find active dynamic terms by contract ID and term template ID
     */
    Flux<ContractTermDynamic> findByContractIdAndTermTemplateIdAndIsActive(Long contractId, 
                                                                           Long termTemplateId, 
                                                                           Boolean isActive);

    /**
     * Find dynamic terms effective at a specific date
     */
    @Query("SELECT * FROM contract_term_dynamic WHERE contract_id = :contractId AND effective_date <= :date AND (expiration_date IS NULL OR expiration_date >= :date) AND is_active = true")
    Flux<ContractTermDynamic> findEffectiveTermsByContractIdAndDate(@Param("contractId") Long contractId, 
                                                                    @Param("date") LocalDateTime date);

    /**
     * Find dynamic terms expiring within a date range
     */
    @Query("SELECT * FROM contract_term_dynamic WHERE expiration_date BETWEEN :fromDate AND :toDate AND is_active = true")
    Flux<ContractTermDynamic> findTermsExpiringBetween(@Param("fromDate") LocalDateTime fromDate, 
                                                       @Param("toDate") LocalDateTime toDate);

    /**
     * Find dynamic terms by text value
     */
    Flux<ContractTermDynamic> findByTermValueText(String termValueText);

    /**
     * Find dynamic terms by numeric value
     */
    Flux<ContractTermDynamic> findByTermValueNumeric(BigDecimal termValueNumeric);

    /**
     * Find dynamic terms with numeric values above threshold
     */
    @Query("SELECT * FROM contract_term_dynamic WHERE term_value_numeric >= :threshold AND is_active = true")
    Flux<ContractTermDynamic> findByTermValueNumericGreaterThanEqual(@Param("threshold") BigDecimal threshold);

    /**
     * Find dynamic terms with JSON values
     */
    @Query("SELECT * FROM contract_term_dynamic WHERE term_value_json IS NOT NULL")
    Flux<ContractTermDynamic> findTermsWithJsonValues();

    /**
     * Count active terms for a contract
     */
    Mono<Long> countByContractIdAndIsActive(Long contractId, Boolean isActive);

    /**
     * Count terms by term template
     */
    Mono<Long> countByTermTemplateId(Long termTemplateId);

    /**
     * Find latest term for a contract and template
     */
    @Query("SELECT * FROM contract_term_dynamic WHERE contract_id = :contractId AND term_template_id = :termTemplateId ORDER BY effective_date DESC LIMIT 1")
    Mono<ContractTermDynamic> findLatestByContractIdAndTermTemplateId(@Param("contractId") Long contractId, 
                                                                      @Param("termTemplateId") Long termTemplateId);

    /**
     * Find terms effective within a date range
     */
    @Query("SELECT * FROM contract_term_dynamic WHERE effective_date BETWEEN :fromDate AND :toDate")
    Flux<ContractTermDynamic> findByEffectiveDateBetween(@Param("fromDate") LocalDateTime fromDate, 
                                                         @Param("toDate") LocalDateTime toDate);

    /**
     * Find expired terms
     */
    @Query("SELECT * FROM contract_term_dynamic WHERE expiration_date < :currentDate AND is_active = true")
    Flux<ContractTermDynamic> findExpiredTerms(@Param("currentDate") LocalDateTime currentDate);
}
