package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.core.contract.core.mappers.contract.v1.ContractStatusHistoryMapper;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractStatusHistoryDTO;
import com.catalis.core.contract.models.entities.contract.v1.ContractStatusHistory;
import com.catalis.core.contract.models.repositories.contract.v1.ContractStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractStatusHistoryUpdateService {

    @Autowired
    private ContractStatusHistoryRepository repository;

    @Autowired
    private ContractStatusHistoryMapper mapper;

    /**
     * Updates an existing ContractStatusHistory entity with the provided data.
     *
     * @param id the unique identifier of the ContractStatusHistory to be updated
     * @param contractStatusHistoryDTO the data transfer object containing the updated details of the ContractStatusHistory
     * @return a Mono emitting the updated ContractStatusHistoryDTO if the update is successful,
     *         or an error if the entity is not found or the update fails
     */
    public Mono<ContractStatusHistoryDTO> updateContractStatusHistory(
            Long id, ContractStatusHistoryDTO contractStatusHistoryDTO) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("ContractStatusHistory with ID " + id + " not found")))
                .flatMap(existingEntity -> {
                    ContractStatusHistory updatedEntity = mapper.toEntity(contractStatusHistoryDTO);
                    updatedEntity.setContractId(contractStatusHistoryDTO.getContractId());
                    updatedEntity.setStatusCode(contractStatusHistoryDTO.getStatusCode());
                    updatedEntity.setStatusStartDate(contractStatusHistoryDTO.getStatusStartDate());
                    updatedEntity.setStatusEndDate(contractStatusHistoryDTO.getStatusEndDate());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDto)
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to update ContractStatusHistory: " + error.getMessage(), error)));
    }

}
