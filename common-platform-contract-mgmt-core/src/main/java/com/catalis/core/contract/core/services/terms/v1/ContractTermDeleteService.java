package com.catalis.core.contract.core.services.terms.v1;

import com.catalis.core.contract.models.repositories.terms.v1.ContractTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractTermDeleteService {

    @Autowired
    private ContractTermRepository repository;

    /**
     * Deletes a contract term identified by the provided contract term ID.
     * This method retrieves the contract term from the repository, deletes it if found,
     * and returns a completion signal. If the contract term is not found, an error is returned.
     *
     * @param contractTermId the ID of the contract term to be deleted
     * @return a {@link Mono} that completes upon successful deletion or emits an error if the contract term is not found or deletion fails
     */
    public Mono<Void> deleteContractTerm(Long contractTermId) {
        return repository.findById(contractTermId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract term with ID " + contractTermId + " not found")))
                .flatMap(contractTerm -> repository.delete(contractTerm))
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to delete contract term: " + error.getMessage(), error)));
    }

}
