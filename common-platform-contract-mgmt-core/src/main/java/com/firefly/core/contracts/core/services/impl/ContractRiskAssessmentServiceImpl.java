package com.firefly.core.contracts.core.services.impl;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.core.mappers.ContractRiskAssessmentMapper;
import com.firefly.core.contracts.core.services.ContractRiskAssessmentService;
import com.firefly.core.contracts.interfaces.dtos.ContractRiskAssessmentDTO;
import com.firefly.core.contracts.models.entities.ContractRiskAssessment;
import com.firefly.core.contracts.models.repositories.ContractRiskAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
@Transactional
public class ContractRiskAssessmentServiceImpl implements ContractRiskAssessmentService {

    @Autowired
    private ContractRiskAssessmentRepository repository;

    @Autowired
    private ContractRiskAssessmentMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractRiskAssessmentDTO>> filterContractRiskAssessments(FilterRequest<ContractRiskAssessmentDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        ContractRiskAssessment.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<ContractRiskAssessmentDTO> createContractRiskAssessment(ContractRiskAssessmentDTO contractRiskAssessmentDTO) {
        return Mono.just(contractRiskAssessmentDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractRiskAssessmentDTO> updateContractRiskAssessment(UUID contractRiskAssessmentId, ContractRiskAssessmentDTO contractRiskAssessmentDTO) {
        return repository.findById(contractRiskAssessmentId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract risk assessment not found with ID: " + contractRiskAssessmentId)))
                .flatMap(existingAssessment -> {
                    ContractRiskAssessment updatedAssessment = mapper.toEntity(contractRiskAssessmentDTO);
                    updatedAssessment.setContractRiskAssessmentId(contractRiskAssessmentId);
                    return repository.save(updatedAssessment);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteContractRiskAssessment(UUID contractRiskAssessmentId) {
        return repository.findById(contractRiskAssessmentId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract risk assessment not found with ID: " + contractRiskAssessmentId)))
                .flatMap(assessment -> repository.deleteById(contractRiskAssessmentId));
    }

    @Override
    public Mono<ContractRiskAssessmentDTO> getContractRiskAssessmentById(UUID contractRiskAssessmentId) {
        return repository.findById(contractRiskAssessmentId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract risk assessment not found with ID: " + contractRiskAssessmentId)))
                .map(mapper::toDTO);
    }
}
