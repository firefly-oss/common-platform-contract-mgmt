package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.contract.core.mappers.contract.v1.ContractPartyMapper;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractPartyDTO;
import com.catalis.core.contract.models.repositories.contract.v1.ContractPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class ContractPartyGetService {

    @Autowired
    private ContractPartyRepository repository;

    @Autowired
    private ContractPartyMapper mapper;

    /**
     * Retrieves a contract party based on the provided contract party ID.
     *
     * @param contractPartyId the unique identifier of the contract party to be retrieved
     * @return a Mono emitting the ContractPartyDTO object if the contract party is found,
     *         or an error if the contract party is not found or an unexpected failure occurs during retrieval
     */
    public Mono<ContractPartyDTO> getContractParty(Long contractPartyId) {
        return repository.findById(contractPartyId)
                .map(mapper::toDto)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract party with ID " + contractPartyId + " not found")))
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to retrieve contract party: " + error.getMessage(), error)));
    }

    /**
     * Retrieves a paginated list of contract parties associated with a specified contract ID.
     *
     * @param contractId the unique identifier of the contract whose parties are to be retrieved
     * @param paginationRequest an object containing pagination and sorting preferences
     * @return a Mono emitting a PaginationResponse containing a list of ContractPartyDTOs and pagination metadata
     */
    public Mono<PaginationResponse<ContractPartyDTO>> findByContractId(Long contractId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDto,
                pageable -> repository.findByContractId(contractId, pageable),
                () -> repository.countByContractId(contractId)
        );
    }



}
