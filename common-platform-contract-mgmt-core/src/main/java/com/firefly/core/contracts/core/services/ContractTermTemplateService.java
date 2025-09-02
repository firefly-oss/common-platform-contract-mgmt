package com.firefly.core.contracts.core.services;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.interfaces.dtos.ContractTermTemplateDTO;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Service interface for managing contract term templates.
 */
public interface ContractTermTemplateService {
    /**
     * Filters the contract term templates based on the given criteria.
     *
     * @param filterRequest the request object containing filtering criteria for ContractTermTemplateDTO
     * @return a reactive {@code Mono} emitting a {@code PaginationResponse} containing the filtered list of contract term templates
     */
    Mono<PaginationResponse<ContractTermTemplateDTO>> filterContractTermTemplates(FilterRequest<ContractTermTemplateDTO> filterRequest);
    
    /**
     * Creates a new contract term template based on the provided information.
     *
     * @param contractTermTemplateDTO the DTO object containing details of the contract term template to be created
     * @return a Mono that emits the created ContractTermTemplateDTO object
     */
    Mono<ContractTermTemplateDTO> createContractTermTemplate(ContractTermTemplateDTO contractTermTemplateDTO);
    
    /**
     * Updates an existing contract term template with updated information.
     *
     * @param termTemplateId the unique identifier of the contract term template to be updated
     * @param contractTermTemplateDTO the data transfer object containing the updated details of the contract term template
     * @return a reactive Mono containing the updated ContractTermTemplateDTO
     */
    Mono<ContractTermTemplateDTO> updateContractTermTemplate(UUID termTemplateId, ContractTermTemplateDTO contractTermTemplateDTO);
    
    /**
     * Deletes a contract term template identified by its unique ID.
     *
     * @param termTemplateId the unique identifier of the contract term template to be deleted
     * @return a Mono that completes when the contract term template is successfully deleted or errors if the deletion fails
     */
    Mono<Void> deleteContractTermTemplate(UUID termTemplateId);
    
    /**
     * Retrieves a contract term template by its unique identifier.
     *
     * @param termTemplateId the unique identifier of the contract term template to retrieve
     * @return a Mono emitting the {@link ContractTermTemplateDTO} representing the contract term template if found,
     *         or an empty Mono if the contract term template does not exist
     */
    Mono<ContractTermTemplateDTO> getContractTermTemplateById(UUID termTemplateId);
}
