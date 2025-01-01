package com.catalis.core.contract.core.services.risk.v1;

import com.catalis.core.contract.core.mappers.risk.v1.ContractRiskAssessmentMapper;
import com.catalis.core.contract.interfaces.dtos.risk.v1.ContractRiskAssessmentDTO;
import com.catalis.core.contract.models.repositories.risk.v1.ContractRiskAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractRiskAssessmentCreateService {

    @Autowired
    private ContractRiskAssessmentRepository repository;

    @Autowired
    private ContractRiskAssessmentMapper mapper;

    /**
     * Creates a new contract risk assessment based on the provided request data.
     *
     * This method converts the input DTO into an entity, saves it to the repository,
     * and maps the saved entity back to a DTO for the response.
     *
     * @param request the data transfer object containing the contract risk assessment details to be created
     * @return a reactive Mono containing the created ContractRiskAssessmentDTO
     */
    public Mono<ContractRiskAssessmentDTO> createContractRiskAssessment(ContractRiskAssessmentDTO request) {
        return Mono.just(request)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .filter(entity -> entity.getContractRiskAssessmentId() != null)
                .map(mapper::toDto);
    }

}
