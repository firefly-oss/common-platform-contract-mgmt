package com.firefly.core.contracts.core.services.impl;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.core.mappers.ContractEventMapper;
import com.firefly.core.contracts.core.services.ContractEventService;
import com.firefly.core.contracts.interfaces.dtos.ContractEventDTO;
import com.firefly.core.contracts.models.entities.ContractEvent;
import com.firefly.core.contracts.models.repositories.ContractEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractEventServiceImpl implements ContractEventService {

    @Autowired
    private ContractEventRepository repository;

    @Autowired
    private ContractEventMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractEventDTO>> filterContractEvents(FilterRequest<ContractEventDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        ContractEvent.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<ContractEventDTO> createContractEvent(ContractEventDTO contractEventDTO) {
        return Mono.just(contractEventDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractEventDTO> updateContractEvent(Long contractEventId, ContractEventDTO contractEventDTO) {
        return repository.findById(contractEventId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract event not found with ID: " + contractEventId)))
                .flatMap(existingEvent -> {
                    ContractEvent updatedEvent = mapper.toEntity(contractEventDTO);
                    updatedEvent.setContractEventId(contractEventId);
                    return repository.save(updatedEvent);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteContractEvent(Long contractEventId) {
        return repository.findById(contractEventId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract event not found with ID: " + contractEventId)))
                .flatMap(event -> repository.deleteById(contractEventId));
    }

    @Override
    public Mono<ContractEventDTO> getContractEventById(Long contractEventId) {
        return repository.findById(contractEventId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract event not found with ID: " + contractEventId)))
                .map(mapper::toDTO);
    }
}
