package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.core.contract.models.repositories.contract.v1.ContractStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractStatusHistoryDeleteService {

    @Autowired
    private ContractStatusHistoryRepository repository;

    /**
     * Deletes a contract status history identified by the provided ID.
     *
     * @param contractStatusHistoryId the unique identifier of the contract status history to be deleted
     * @return a Mono that completes when the contract status history has been successfully deleted,
     *         or emits an error if the contract status history does not exist or deletion fails
     */
    public Mono<Void> deleteContractStatusHistory(Long contractStatusHistoryId) {
        return repository.findById(contractStatusHistoryId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract status history with ID " + contractStatusHistoryId + " not found")))
                .flatMap(contractStatusHistory -> repository.delete(contractStatusHistory))
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to delete contract status history: " + error.getMessage(), error)));
    }

}
