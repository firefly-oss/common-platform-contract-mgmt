package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.contract.core.mappers.contract.v1.ContractMapper;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractDTO;
import com.catalis.core.contract.models.repositories.contract.v1.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class ContractGetService {

    @Autowired
    private ContractRepository repository;

    @Autowired
    private ContractMapper mapper;

    /**
     * Retrieves a contract based on the provided contract ID.
     *
     * @param contractId the unique identifier of the contract to be retrieved
     * @return a Mono emitting the contract data transfer object if found,
     *         or an error if the contract is not found or retrieval fails
     */
    public Mono<ContractDTO> getContract(Long contractId) {
        return repository.findById(contractId)
                .map(mapper::toDto)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract with ID " + contractId + " not found")))
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to retrieve contract: " + error.getMessage(), error)));
    }

    /**
     * Retrieves a contract based on its contract number.
     *
     * @param contractNumber the unique identifier of the contract in string format
     * @return a Mono emitting the ContractDTO object if a contract with the specified number is found;
     *         otherwise, emits an error if the contract is not found or if any other failure occurs during retrieval
     */
    public Mono<ContractDTO> getContractByContractNumber(String contractNumber) {
        return repository.findByContractNumber(contractNumber)
                .map(mapper::toDto)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract with number " + contractNumber + " not found")))
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to retrieve contract: " + error.getMessage(), error)));
    }

    /**
     * Retrieves a paginated list of contracts associated with a specified product ID.
     *
     * @param productId the unique identifier of the product whose associated contracts are to be retrieved
     * @param paginationRequest the object containing pagination and sorting preferences
     * @return a Mono emitting a PaginationResponse containing a list of ContractDTOs and pagination metadata
     */
    public Mono<PaginationResponse<ContractDTO>> findByProductId(Long productId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDto,
                pageable -> repository.findByProductId(productId, pageable),
                () -> repository.countByProductId(productId)
        );
    }
    
    

}
