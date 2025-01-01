package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.core.contract.core.mappers.contract.v1.ContractPartyMapper;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractPartyDTO;
import com.catalis.core.contract.models.entities.contract.v1.ContractParty;
import com.catalis.core.contract.models.repositories.contract.v1.ContractPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractPartyUpdateService {

    @Autowired
    private ContractPartyRepository repository;

    @Autowired
    private ContractPartyMapper mapper;

    /**
     * Updates an existing ContractParty entity with the values provided in the request DTO.
     * If any field in the request DTO is null, the corresponding field from the existing entity is retained.
     *
     * @param id The unique identifier of the ContractParty entity to be updated.
     * @param request The ContractPartyDTO object containing the updated values.
     * @return A Mono emitting the updated ContractPartyDTO object.
     *         Emits an error if the specified ID is not found or if the update operation fails.
     */
    public Mono<ContractPartyDTO> updateContractParty(Long id, ContractPartyDTO request) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract Party with ID " + id + " not found")))
                .flatMap(existingEntity -> {
                    ContractParty updatedEntity = mapper.toEntity(request);
                    updatedEntity.setContractId(request.getContractId() != null ? request.getContractId() : existingEntity.getContractId());
                    updatedEntity.setPartyId(request.getPartyId() != null ? request.getPartyId() : existingEntity.getPartyId());
                    updatedEntity.setRoleInContract(request.getRoleInContract() != null ? request.getRoleInContract() : existingEntity.getRoleInContract());
                    updatedEntity.setDateJoined(request.getDateJoined() != null ? request.getDateJoined() : existingEntity.getDateJoined());
                    updatedEntity.setDateLeft(request.getDateLeft() != null ? request.getDateLeft() : existingEntity.getDateLeft());
                    updatedEntity.setActive(request.getActive() != null ? request.getActive() : existingEntity.getActive());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDto)
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to update contract party: " + error.getMessage(), error)));
    }

}
