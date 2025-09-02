package com.firefly.core.contracts.core.services.impl;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.core.mappers.ContractPartyMapper;
import com.firefly.core.contracts.core.services.ContractPartyService;
import com.firefly.core.contracts.interfaces.dtos.ContractPartyDTO;
import com.firefly.core.contracts.models.entities.ContractParty;
import com.firefly.core.contracts.models.repositories.ContractPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
@Transactional
public class ContractPartyServiceImpl implements ContractPartyService {

    @Autowired
    private ContractPartyRepository repository;

    @Autowired
    private ContractPartyMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractPartyDTO>> filterContractParties(FilterRequest<ContractPartyDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        ContractParty.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<ContractPartyDTO> createContractParty(ContractPartyDTO contractPartyDTO) {
        return Mono.just(contractPartyDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractPartyDTO> updateContractParty(UUID contractPartyId, ContractPartyDTO contractPartyDTO) {
        return repository.findById(contractPartyId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract party not found with ID: " + contractPartyId)))
                .flatMap(existingParty -> {
                    ContractParty updatedParty = mapper.toEntity(contractPartyDTO);
                    updatedParty.setContractPartyId(contractPartyId);
                    return repository.save(updatedParty);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteContractParty(UUID contractPartyId) {
        return repository.findById(contractPartyId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract party not found with ID: " + contractPartyId)))
                .flatMap(party -> repository.deleteById(contractPartyId));
    }

    @Override
    public Mono<ContractPartyDTO> getContractPartyById(UUID contractPartyId) {
        return repository.findById(contractPartyId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract party not found with ID: " + contractPartyId)))
                .map(mapper::toDTO);
    }
}
