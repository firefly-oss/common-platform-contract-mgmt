package com.firefly.core.contracts.models.repositories;

import com.firefly.core.contracts.interfaces.enums.TermValidationTypeEnum;
import com.firefly.core.contracts.models.entities.ContractTermValidationRule;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository interface for ContractTermValidationRule entity operations
 */
@Repository
public interface ContractTermValidationRuleRepository extends BaseRepository<ContractTermValidationRule, Long> {

    /**
     * Find validation rules by term template ID
     */
    Flux<ContractTermValidationRule> findByTermTemplateId(Long termTemplateId);

    /**
     * Find validation rules by validation type
     */
    Flux<ContractTermValidationRule> findByValidationType(TermValidationTypeEnum validationType);

    /**
     * Find validation rules by term template ID and validation type
     */
    Flux<ContractTermValidationRule> findByTermTemplateIdAndValidationType(Long termTemplateId, 
                                                                           TermValidationTypeEnum validationType);

    /**
     * Find validation rules by validation value
     */
    Flux<ContractTermValidationRule> findByValidationValue(String validationValue);

    /**
     * Count validation rules for a term template
     */
    Mono<Long> countByTermTemplateId(Long termTemplateId);

    /**
     * Count validation rules by validation type
     */
    Mono<Long> countByValidationType(TermValidationTypeEnum validationType);

    /**
     * Find required validation rules
     */
    @Query("SELECT * FROM contract_term_validation_rule WHERE validation_type = 'REQUIRED'")
    Flux<ContractTermValidationRule> findRequiredValidationRules();

    /**
     * Find validation rules with custom error messages
     */
    @Query("SELECT * FROM contract_term_validation_rule WHERE error_message IS NOT NULL AND error_message != ''")
    Flux<ContractTermValidationRule> findRulesWithCustomErrorMessages();

    /**
     * Find validation rules by term template ID ordered by validation type
     */
    Flux<ContractTermValidationRule> findByTermTemplateIdOrderByValidationType(Long termTemplateId);

    /**
     * Delete validation rules by term template ID
     */
    @Query("DELETE FROM contract_term_validation_rule WHERE term_template_id = :termTemplateId")
    Mono<Void> deleteByTermTemplateId(@Param("termTemplateId") Long termTemplateId);

    /**
     * Find validation rules for multiple term templates
     */
    @Query("SELECT * FROM contract_term_validation_rule WHERE term_template_id IN (:termTemplateIds)")
    Flux<ContractTermValidationRule> findByTermTemplateIdIn(@Param("termTemplateIds") Iterable<Long> termTemplateIds);

    /**
     * Check if term template has validation rules
     */
    @Query("SELECT COUNT(*) > 0 FROM contract_term_validation_rule WHERE term_template_id = :termTemplateId")
    Mono<Boolean> existsByTermTemplateId(@Param("termTemplateId") Long termTemplateId);
}
