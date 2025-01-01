package com.catalis.core.contract.core.services.terms.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.contract.core.mappers.terms.v1.ContractTermMapper;
import com.catalis.core.contract.interfaces.dtos.terms.v1.ContractTermDTO;
import com.catalis.core.contract.models.repositories.terms.v1.ContractTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class ContractTermGetService {

    @Autowired
    private ContractTermRepository repository;

    @Autowired
    private ContractTermMapper mapper;

    /**
     * Retrieves a contract term based on the provided contract term ID.
     * This method fetches the contract term from the repository, maps it to a DTO,
     * and returns it as a reactive Mono stream. If the contract term is not found,
     * or any error occurs during the retrieval process, an appropriate error is returned.
     *
     * @param contractTermId the ID of the contract term to retrieve
     * @return a {@link Mono} emitting the retrieved {@link ContractTermDTO}
     *         or an error if the contract term is not found or retrieval fails
     */
    public Mono<ContractTermDTO> getContractTerm(Long contractTermId) {
        return repository.findById(contractTermId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract term with ID " + contractTermId + " not found")))
                .map(mapper::toDto)
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to retrieve contract term: " + error.getMessage(), error)));
    }

    /**
     * Retrieves a paginated list of contract terms associated with a given contract ID.
     * This method performs a query using the specified contract ID and pagination criteria,
     * and maps the resulting entities to DTOs.
     *
     * @param contractId the ID of the contract whose terms are to be retrieved
     * @param paginationRequest the pagination parameters including page size and sorting details
     * @return a {@link Mono} emitting a {@link PaginationResponse} containing the list of {@link ContractTermDTO} objects and pagination metadata
     */
    public Mono<PaginationResponse<ContractTermDTO>> findByContractId(
            Long contractId, PaginationRequest paginationRequest){
        
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDto,
                pageable -> repository.findByContractId(contractId, pageable),
                () -> repository.countByContractId(contractId)
        );

    }

}
