package com.firefly.core.contracts.core.services.impl;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.core.mappers.ContractTermValidationRuleMapper;
import com.firefly.core.contracts.core.services.ContractTermValidationRuleService;
import com.firefly.core.contracts.interfaces.dtos.ContractTermValidationRuleDTO;
import com.firefly.core.contracts.models.entities.ContractTermValidationRule;
import com.firefly.core.contracts.models.repositories.ContractTermValidationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractTermValidationRuleServiceImpl implements ContractTermValidationRuleService {

    @Autowired
    private ContractTermValidationRuleRepository repository;

    @Autowired
    private ContractTermValidationRuleMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractTermValidationRuleDTO>> filterContractTermValidationRules(FilterRequest<ContractTermValidationRuleDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        ContractTermValidationRule.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<ContractTermValidationRuleDTO> createContractTermValidationRule(ContractTermValidationRuleDTO contractTermValidationRuleDTO) {
        return Mono.just(contractTermValidationRuleDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractTermValidationRuleDTO> updateContractTermValidationRule(Long validationRuleId, ContractTermValidationRuleDTO contractTermValidationRuleDTO) {
        return repository.findById(validationRuleId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract term validation rule not found with ID: " + validationRuleId)))
                .flatMap(existingRule -> {
                    ContractTermValidationRule updatedRule = mapper.toEntity(contractTermValidationRuleDTO);
                    updatedRule.setValidationRuleId(validationRuleId);
                    return repository.save(updatedRule);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteContractTermValidationRule(Long validationRuleId) {
        return repository.findById(validationRuleId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract term validation rule not found with ID: " + validationRuleId)))
                .flatMap(rule -> repository.deleteById(validationRuleId));
    }

    @Override
    public Mono<ContractTermValidationRuleDTO> getContractTermValidationRuleById(Long validationRuleId) {
        return repository.findById(validationRuleId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract term validation rule not found with ID: " + validationRuleId)))
                .map(mapper::toDTO);
    }
}
