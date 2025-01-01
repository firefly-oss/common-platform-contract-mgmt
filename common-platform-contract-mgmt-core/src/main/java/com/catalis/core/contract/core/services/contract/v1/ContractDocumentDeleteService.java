package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.core.contract.models.repositories.contract.v1.ContractDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractDocumentDeleteService {

    @Autowired
    private ContractDocumentRepository repository;

    /**
     * Deletes a contract document identified by the provided contract document ID.
     *
     * @param contractDocumentId the unique identifier of the contract document to be deleted
     * @return a Mono that completes when the contract document has been successfully deleted or emits an error
     *         if the contract document does not exist or deletion fails
     */
    public Mono<Void> deleteContractDocument(Long contractDocumentId) {
        return repository.findById(contractDocumentId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract document with ID " + contractDocumentId + " not found")))
                .flatMap(contractDocument -> repository.delete(contractDocument))
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to delete contract document: " + error.getMessage(), error)));
    }

}
