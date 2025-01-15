package com.catalis.core.contract.core.services.risk.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.contract.core.mappers.risk.v1.ContractRiskAssessmentMapper;
import com.catalis.core.contract.interfaces.dtos.risk.v1.ContractRiskAssessmentDTO;
import com.catalis.core.contract.models.entities.risk.v1.ContractRiskAssessment;
import com.catalis.core.contract.models.repositories.risk.v1.ContractRiskAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractRiskAssessmentServiceImpl implements ContractRiskAssessmentService {

    @Autowired
    private ContractRiskAssessmentRepository repository;

    @Autowired
    private ContractRiskAssessmentMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractRiskAssessmentDTO>> getAllRiskAssessments(Long contractId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDto,
                pageable -> repository.findByContractId(contractId, pageable),
                () -> repository.countByContractId(contractId)
        );
    }

    @Override
    public Mono<ContractRiskAssessmentDTO> createRiskAssessment(Long contractId, ContractRiskAssessmentDTO dto) {
        ContractRiskAssessment entity = mapper.toEntity(dto);
        entity.setContractId(contractId);
        return repository.save(entity)
                .map(mapper::toDto);
    }

    @Override
    public Mono<ContractRiskAssessmentDTO> getRiskAssessment(Long contractId, Long riskId) {
        return repository.findById(riskId)
                .filter(entity -> entity.getContractId().equals(contractId))
                .map(mapper::toDto);
    }

    @Override
    public Mono<ContractRiskAssessmentDTO> updateRiskAssessment(Long contractId, Long riskId, ContractRiskAssessmentDTO dto) {
        return repository.findById(riskId)
                .filter(entity -> entity.getContractId().equals(contractId))
                .flatMap(entity -> {
                    ContractRiskAssessment updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setContractRiskAssessmentId(riskId);
                    updatedEntity.setContractId(contractId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> deleteRiskAssessment(Long contractId, Long riskId) {
        return repository.findById(riskId)
                .filter(entity -> entity.getContractId().equals(contractId))
                .flatMap(repository::delete);
    }
}
