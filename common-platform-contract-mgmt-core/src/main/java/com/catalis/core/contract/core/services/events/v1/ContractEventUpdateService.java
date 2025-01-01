package com.catalis.core.contract.core.services.events.v1;

import com.catalis.core.contract.core.mappers.events.v1.ContractEventMapper;
import com.catalis.core.contract.interfaces.dtos.events.v1.ContractEventDTO;
import com.catalis.core.contract.models.entities.events.v1.ContractEvent;
import com.catalis.core.contract.models.repositories.events.v1.ContractEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractEventUpdateService {

    @Autowired
    private ContractEventRepository repository;

    @Autowired
    private ContractEventMapper mapper;

    /**
     * Updates an existing contract event by its ID. The method retrieves the contract event
     * from the repository, updates its details with the given data transfer object (DTO),
     * and saves the updated entity. If the contract event is not found or an error occurs
     * during the update process, a corresponding error is returned.
     *
     * @param id The ID of the contract event to update.
     * @param contractEvent The {@link ContractEventDTO} containing the updated details for the contract event.
     * @return A {@link Mono} emitting the updated {@link ContractEventDTO}, or an error if the contract event
     *         cannot be found or if there is an issue during the update operation.
     */
    public Mono<ContractEventDTO> updateContractEvent(Long id, ContractEventDTO contractEvent) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract event with ID " + id + " not found")))
                .flatMap(existingEvent -> {
                    ContractEvent updatedEntity = mapper.toEntity(contractEvent);
                    updatedEntity.setContractId(existingEvent.getContractId());
                    updatedEntity.setEventType(existingEvent.getEventType());
                    updatedEntity.setEventDate(existingEvent.getEventDate());
                    updatedEntity.setEventDescription(existingEvent.getEventDescription());
                    updatedEntity.setDocumentManagerRef(existingEvent.getDocumentManagerRef());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDto)
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to update contract event: " + error.getMessage(), error)));
    }

}
