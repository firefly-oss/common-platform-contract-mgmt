package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.core.contract.models.repositories.contract.v1.ContractPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractPartyDeleteService {

    @Autowired
    private ContractPartyRepository repository;

    /**
     * Deletes a contract party identified by the provided ID.
     *
     * @param contractPartyId the unique identifier of the contract party to be deleted
     * @return a Mono that completes when the contract party has been successfully deleted,
     *         or emits an error if the contract party does not exist or deletion fails
     */
    public Mono<Void> deleteContractParty(Long contractPartyId) {
        return repository.findById(contractPartyId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract party with ID " + contractPartyId + " not found")))
                .flatMap(contractParty -> repository.delete(contractParty))
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to delete contract party: " + error.getMessage(), error)));
    }

}
