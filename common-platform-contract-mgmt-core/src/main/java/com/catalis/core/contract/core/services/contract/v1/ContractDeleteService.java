package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.core.contract.models.repositories.contract.v1.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractDeleteService {

    @Autowired
    private ContractRepository repository;

    /**
     * Deletes a contract identified by the provided contract ID.
     *
     * @param contractId the unique identifier of the contract to be deleted
     * @return a Mono that completes when the contract has been successfully deleted or emits an error if the contract does not exist or deletion fails
     */
    public Mono<Void> deleteContract(Long contractId) {
        return repository.findById(contractId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract with ID " + contractId + " not found")))
                .flatMap(contract -> repository.delete(contract))
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to delete contract: " + error.getMessage(), error)));
    }

}
