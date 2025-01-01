package com.catalis.core.contract.core.services.risk.v1;

import com.catalis.core.contract.models.repositories.risk.v1.ContractRiskAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractRiskAssessmentDeleteService {

    @Autowired
    private ContractRiskAssessmentRepository repository;

    /**
     * Deletes a contract risk assessment by its unique identifier.
     *
     * This method retrieves the contract risk assessment with the specified ID from the repository.
     * If found, the contract risk assessment is deleted; otherwise, an error is returned.
     *
     * @param contractRiskAssessmentId the unique identifier of the contract risk assessment to be deleted
     * @return a reactive Mono that completes when the deletion is successful or emits an error if not
     */
    public Mono<Void> deleteContractRiskAssessment(Long contractRiskAssessmentId) {
        return repository.findById(contractRiskAssessmentId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract risk assessment with ID " + contractRiskAssessmentId + " not found")))
                .flatMap(contractRiskAssessment -> repository.delete(contractRiskAssessment))
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to delete contract risk assessment: " + error.getMessage(), error)));
    }

}
