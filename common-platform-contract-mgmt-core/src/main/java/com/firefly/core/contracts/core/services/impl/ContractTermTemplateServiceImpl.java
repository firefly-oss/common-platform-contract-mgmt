package com.firefly.core.contracts.core.services.impl;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.core.mappers.ContractTermTemplateMapper;
import com.firefly.core.contracts.core.services.ContractTermTemplateService;
import com.firefly.core.contracts.interfaces.dtos.ContractTermTemplateDTO;
import com.firefly.core.contracts.models.entities.ContractTermTemplate;
import com.firefly.core.contracts.models.repositories.ContractTermTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractTermTemplateServiceImpl implements ContractTermTemplateService {

    @Autowired
    private ContractTermTemplateRepository repository;

    @Autowired
    private ContractTermTemplateMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractTermTemplateDTO>> filterContractTermTemplates(FilterRequest<ContractTermTemplateDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        ContractTermTemplate.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<ContractTermTemplateDTO> createContractTermTemplate(ContractTermTemplateDTO contractTermTemplateDTO) {
        return Mono.just(contractTermTemplateDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractTermTemplateDTO> updateContractTermTemplate(Long termTemplateId, ContractTermTemplateDTO contractTermTemplateDTO) {
        return repository.findById(termTemplateId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract term template not found with ID: " + termTemplateId)))
                .flatMap(existingTemplate -> {
                    ContractTermTemplate updatedTemplate = mapper.toEntity(contractTermTemplateDTO);
                    updatedTemplate.setTermTemplateId(termTemplateId);
                    return repository.save(updatedTemplate);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteContractTermTemplate(Long termTemplateId) {
        return repository.findById(termTemplateId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract term template not found with ID: " + termTemplateId)))
                .flatMap(template -> repository.deleteById(termTemplateId));
    }

    @Override
    public Mono<ContractTermTemplateDTO> getContractTermTemplateById(Long termTemplateId) {
        return repository.findById(termTemplateId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract term template not found with ID: " + termTemplateId)))
                .map(mapper::toDTO);
    }
}
