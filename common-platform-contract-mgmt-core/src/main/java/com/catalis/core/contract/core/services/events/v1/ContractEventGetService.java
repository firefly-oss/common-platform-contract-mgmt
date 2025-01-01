package com.catalis.core.contract.core.services.events.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.contract.core.mappers.events.v1.ContractEventMapper;
import com.catalis.core.contract.interfaces.dtos.events.v1.ContractEventDTO;
import com.catalis.core.contract.models.repositories.events.v1.ContractEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class ContractEventGetService {

    @Autowired
    private ContractEventRepository repository;

    @Autowired
    private ContractEventMapper mapper;

    /**
     * Retrieves a contract event by its ID, maps it to a data transfer object (DTO),
     * and returns it as a reactive Mono. If the contract event is not found or an
     * error occurs during retrieval, the Mono emits an error.
     *
     * @param contractEventId The ID of the contract event to be retrieved.
     * @return A {@link Mono} emitting the {@link ContractEventDTO} corresponding to the specified ID,
     *         or an error if the contract event cannot be found or if there is an issue during the retrieval process.
     */
    public Mono<ContractEventDTO> getContractEvent(Long contractEventId) {
        return repository.findById(contractEventId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract event with ID " + contractEventId + " not found")))
                .map(mapper::toDto)
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to retrieve contract event: " + error.getMessage(), error)));
    }

    /**
     * Retrieves a paginated list of contract events associated with the specified contract ID,
     * ordered by event date in descending order.
     *
     * @param contractId The ID of the contract for which events are to be retrieved.
     * @param paginationRequest The details of the pagination, such as page number and size.
     * @return A {@link Mono} emitting a {@link PaginationResponse} containing a list of {@link ContractEventDTO},
     *         or an error if the retrieval operation fails.
     */
    public Mono<PaginationResponse<ContractEventDTO>> findByContractIdOrderByEventDateDesc(
            Long contractId, PaginationRequest paginationRequest) {

        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDto,
                pageable -> repository.findByContractIdOrderByEventDateDesc(contractId, pageable),
                () -> repository.countByContractId(contractId)
        );

    }

}
