package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.core.contract.core.mappers.contract.v1.ContractMapper;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractDTO;
import com.catalis.core.contract.models.entities.contract.v1.Contract;
import com.catalis.core.contract.models.repositories.contract.v1.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractUpdateService {
    
    @Autowired
    private ContractRepository repository;
    
    @Autowired
    private ContractMapper mapper;

    /**
     * Updates the contract with the specified ID using the provided contract details.
     * If the contract with the given ID is not found, an error is returned.
     * The updated contract is saved to the repository and converted back to a DTO.
     *
     * @param id the ID of the contract to update
     * @param request the updated contract details to apply
     * @return a Mono emitting the updated contract as a DTO or an error if updating fails
     */
    public Mono<ContractDTO> updateContract(Long id, ContractDTO request) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract with ID " + id + " not found")))
                .flatMap(existingContract -> {
                    Contract updatedContract = mapper.toEntity(request);
                    updatedContract.setContractId(existingContract.getContractId());
                    updatedContract.setContractNumber(request.getContractNumber());
                    updatedContract.setProductId(request.getProductId());
                    updatedContract.setContractType(request.getContractType());
                    updatedContract.setContractStatus(request.getContractStatus());
                    updatedContract.setStartDate(request.getStartDate());
                    updatedContract.setEndDate(request.getEndDate());
                    updatedContract.setDocumentManagerRef(request.getDocumentManagerRef());
                    return repository.save(updatedContract);
                })
                .map(mapper::toDto)
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to update contract: " + error.getMessage(), error)));
    }
    
}
