package com.firefly.core.contracts.core.services.impl;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.core.mappers.ContractTermDynamicMapper;
import com.firefly.core.contracts.core.services.ContractTermDynamicService;
import com.firefly.core.contracts.interfaces.dtos.ContractTermDynamicDTO;
import com.firefly.core.contracts.models.entities.ContractTermDynamic;
import com.firefly.core.contracts.models.repositories.ContractTermDynamicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractTermDynamicServiceImpl implements ContractTermDynamicService {

    @Autowired
    private ContractTermDynamicRepository repository;

    @Autowired
    private ContractTermDynamicMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractTermDynamicDTO>> filterContractTermDynamics(FilterRequest<ContractTermDynamicDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        ContractTermDynamic.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<ContractTermDynamicDTO> createContractTermDynamic(ContractTermDynamicDTO contractTermDynamicDTO) {
        return Mono.just(contractTermDynamicDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractTermDynamicDTO> updateContractTermDynamic(Long termId, ContractTermDynamicDTO contractTermDynamicDTO) {
        return repository.findById(termId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract term dynamic not found with ID: " + termId)))
                .flatMap(existingTerm -> {
                    ContractTermDynamic updatedTerm = mapper.toEntity(contractTermDynamicDTO);
                    updatedTerm.setTermId(termId);
                    return repository.save(updatedTerm);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteContractTermDynamic(Long termId) {
        return repository.findById(termId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract term dynamic not found with ID: " + termId)))
                .flatMap(term -> repository.deleteById(termId));
    }

    @Override
    public Mono<ContractTermDynamicDTO> getContractTermDynamicById(Long termId) {
        return repository.findById(termId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract term dynamic not found with ID: " + termId)))
                .map(mapper::toDTO);
    }
}
