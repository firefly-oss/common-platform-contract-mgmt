package com.firefly.core.contracts.core.services.impl;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.core.mappers.ContractStatusHistoryMapper;
import com.firefly.core.contracts.core.services.ContractStatusHistoryService;
import com.firefly.core.contracts.interfaces.dtos.ContractStatusHistoryDTO;
import com.firefly.core.contracts.models.entities.ContractStatusHistory;
import com.firefly.core.contracts.models.repositories.ContractStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
@Transactional
public class ContractStatusHistoryServiceImpl implements ContractStatusHistoryService {

    @Autowired
    private ContractStatusHistoryRepository repository;

    @Autowired
    private ContractStatusHistoryMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractStatusHistoryDTO>> filterContractStatusHistory(FilterRequest<ContractStatusHistoryDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        ContractStatusHistory.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<ContractStatusHistoryDTO> createContractStatusHistory(ContractStatusHistoryDTO contractStatusHistoryDTO) {
        return Mono.just(contractStatusHistoryDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractStatusHistoryDTO> updateContractStatusHistory(UUID contractStatusHistoryId, ContractStatusHistoryDTO contractStatusHistoryDTO) {
        return repository.findById(contractStatusHistoryId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract status history not found with ID: " + contractStatusHistoryId)))
                .flatMap(existingHistory -> {
                    ContractStatusHistory updatedHistory = mapper.toEntity(contractStatusHistoryDTO);
                    updatedHistory.setContractStatusHistoryId(contractStatusHistoryId);
                    return repository.save(updatedHistory);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteContractStatusHistory(UUID contractStatusHistoryId) {
        return repository.findById(contractStatusHistoryId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract status history not found with ID: " + contractStatusHistoryId)))
                .flatMap(history -> repository.deleteById(contractStatusHistoryId));
    }

    @Override
    public Mono<ContractStatusHistoryDTO> getContractStatusHistoryById(UUID contractStatusHistoryId) {
        return repository.findById(contractStatusHistoryId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract status history not found with ID: " + contractStatusHistoryId)))
                .map(mapper::toDTO);
    }
}
