package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.contract.core.mappers.contract.v1.ContractStatusHistoryMapper;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractStatusHistoryDTO;
import com.catalis.core.contract.models.repositories.contract.v1.ContractStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class ContractStatusHistoryGetService {

    @Autowired
    private ContractStatusHistoryRepository repository;

    @Autowired
    private ContractStatusHistoryMapper mapper;

    /**
     * Retrieves the contract status history based on the provided ID.
     *
     * @param contractStatusHistoryId the unique identifier of the contract status history to be retrieved
     * @return a Mono emitting the ContractStatusHistoryDTO if found,
     *         or an error if the contract status history with the specified ID does not exist
     */
    public Mono<ContractStatusHistoryDTO> getContractStatusHistory(Long contractStatusHistoryId) {
        return repository.findById(contractStatusHistoryId)
                .map(mapper::toDto)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract status history with ID " + contractStatusHistoryId + " not found")));
    }

    /**
     * Retrieves a paginated list of contract status history entries associated with a specified contract ID.
     * The results are ordered in descending order by the status start date.
     *
     * @param contractId the unique identifier of the contract whose status history is to be retrieved
     * @param paginationRequest the object containing pagination and sorting preferences
     * @return a Mono emitting a PaginationResponse containing a list of ContractStatusHistoryDTOs and pagination metadata
     */
    public Mono<PaginationResponse<ContractStatusHistoryDTO>> findByContractIdOrderByStatusStartDateDesc(
            Long contractId, PaginationRequest paginationRequest){

        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDto,
                pageable -> repository.findByContractIdOrderByStatusStartDateDesc(contractId, pageable),
                () -> repository.countByContractId(contractId)
        );

    }

}
