package com.catalis.core.contract.core.services.terms.v1;

import com.catalis.core.contract.core.mappers.terms.v1.ContractTermMapper;
import com.catalis.core.contract.interfaces.dtos.terms.v1.ContractTermDTO;
import com.catalis.core.contract.models.entities.terms.v1.ContractTerm;
import com.catalis.core.contract.models.repositories.terms.v1.ContractTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractTermUpdateService {

    @Autowired
    private ContractTermRepository repository;

    @Autowired
    private ContractTermMapper mapper;

    /**
     * Updates an existing contract term by its identifier.
     *
     * @param id The identifier of the contract term to update.
     * @param contractTerm The updated contract term details.
     * @return A {@code Mono<ContractTermDTO>} containing the updated contract term details.
     *         If the contract term with the given ID is not found, returns an error.
     */
    public Mono<ContractTermDTO> updateContractTerm(Long id, ContractTermDTO contractTerm) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract term with ID " + id + " not found")))
                .map(existingContractTerm -> {
                    ContractTerm updatedEntity = mapper.toEntity(contractTerm);
                    updatedEntity.setContractId(contractTerm.getContractId());
                    updatedEntity.setTermType(contractTerm.getTermType());
                    updatedEntity.setTermDescription(contractTerm.getTermDescription());
                    updatedEntity.setNumericValue(contractTerm.getNumericValue());
                    updatedEntity.setValueUnit(contractTerm.getValueUnit());
                    updatedEntity.setEffectiveDate(contractTerm.getEffectiveDate());
                    updatedEntity.setExpirationDate(contractTerm.getExpirationDate());
                    return updatedEntity;
                })
                .flatMap(repository::save)
                .map(mapper::toDto)
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to update contract term: " + error.getMessage(), error)));
    }

}