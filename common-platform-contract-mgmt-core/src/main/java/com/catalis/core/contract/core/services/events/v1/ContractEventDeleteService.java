package com.catalis.core.contract.core.services.events.v1;

import com.catalis.core.contract.models.repositories.events.v1.ContractEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractEventDeleteService {

    @Autowired
    private ContractEventRepository repository;

    /**
     * Deletes a contract event with the specified ID from the repository.
     *
     * @param contractEventId The ID of the contract event to be deleted.
     * @return A {@link Mono} that completes when the deletion operation is successful,
     *         or emits an error if the contract event with the given ID does not exist or the operation fails.
     */
    public Mono<Void> deleteContractEvent(Long contractEventId) {
        return repository.findById(contractEventId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract event with ID " + contractEventId + " not found")))
                .flatMap(contractEvent -> repository.delete(contractEvent))
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to delete contract event: " + error.getMessage(), error)));
    }

}
