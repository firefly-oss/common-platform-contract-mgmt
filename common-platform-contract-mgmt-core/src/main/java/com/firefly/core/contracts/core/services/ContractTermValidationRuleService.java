package com.firefly.core.contracts.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.interfaces.dtos.ContractTermValidationRuleDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing contract term validation rules.
 */
public interface ContractTermValidationRuleService {
    /**
     * Filters the contract term validation rules based on the given criteria.
     *
     * @param filterRequest the request object containing filtering criteria for ContractTermValidationRuleDTO
     * @return a reactive {@code Mono} emitting a {@code PaginationResponse} containing the filtered list of contract term validation rules
     */
    Mono<PaginationResponse<ContractTermValidationRuleDTO>> filterContractTermValidationRules(FilterRequest<ContractTermValidationRuleDTO> filterRequest);
    
    /**
     * Creates a new contract term validation rule based on the provided information.
     *
     * @param contractTermValidationRuleDTO the DTO object containing details of the contract term validation rule to be created
     * @return a Mono that emits the created ContractTermValidationRuleDTO object
     */
    Mono<ContractTermValidationRuleDTO> createContractTermValidationRule(ContractTermValidationRuleDTO contractTermValidationRuleDTO);
    
    /**
     * Updates an existing contract term validation rule with updated information.
     *
     * @param validationRuleId the unique identifier of the contract term validation rule to be updated
     * @param contractTermValidationRuleDTO the data transfer object containing the updated details of the contract term validation rule
     * @return a reactive Mono containing the updated ContractTermValidationRuleDTO
     */
    Mono<ContractTermValidationRuleDTO> updateContractTermValidationRule(Long validationRuleId, ContractTermValidationRuleDTO contractTermValidationRuleDTO);
    
    /**
     * Deletes a contract term validation rule identified by its unique ID.
     *
     * @param validationRuleId the unique identifier of the contract term validation rule to be deleted
     * @return a Mono that completes when the contract term validation rule is successfully deleted or errors if the deletion fails
     */
    Mono<Void> deleteContractTermValidationRule(Long validationRuleId);
    
    /**
     * Retrieves a contract term validation rule by its unique identifier.
     *
     * @param validationRuleId the unique identifier of the contract term validation rule to retrieve
     * @return a Mono emitting the {@link ContractTermValidationRuleDTO} representing the contract term validation rule if found,
     *         or an empty Mono if the contract term validation rule does not exist
     */
    Mono<ContractTermValidationRuleDTO> getContractTermValidationRuleById(Long validationRuleId);
}
