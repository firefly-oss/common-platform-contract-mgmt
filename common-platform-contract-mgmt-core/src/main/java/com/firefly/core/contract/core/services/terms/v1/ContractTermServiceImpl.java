package com.firefly.core.contract.core.services.terms.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.contract.core.mappers.terms.v1.ContractTermMapper;
import com.firefly.core.contract.interfaces.dtos.terms.v1.ContractTermDTO;
import com.firefly.core.contract.models.entities.terms.v1.ContractTerm;
import com.firefly.core.contract.models.repositories.terms.v1.ContractTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractTermServiceImpl implements ContractTermService {

    @Autowired
    private ContractTermRepository repository;

    @Autowired
    private ContractTermMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractTermDTO>> getAllTerms(Long contractId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDto,
                pageable -> repository.findByContractId(contractId, pageable),
                () -> repository.countByContractId(contractId)
        );
    }

    @Override
    public Mono<ContractTermDTO> createTerm(Long contractId, ContractTermDTO dto) {
        ContractTerm entity = mapper.toEntity(dto);
        entity.setContractId(contractId);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDto);
    }

    @Override
    public Mono<ContractTermDTO> getTerm(Long contractId, Long termId) {
        return repository.findById(termId)
                .filter(term -> term.getContractId().equals(contractId))
                .map(mapper::toDto);
    }

    @Override
    public Mono<ContractTermDTO> updateTerm(Long contractId, Long termId, ContractTermDTO dto) {
        return repository.findById(termId)
                .filter(term -> term.getContractId().equals(contractId))
                .flatMap(existing -> {
                    ContractTerm updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setContractTermId(termId);
                    updatedEntity.setContractId(contractId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> deleteTerm(Long contractId, Long termId) {
        return repository.findById(termId)
                .filter(term -> term.getContractId().equals(contractId))
                .flatMap(repository::delete);
    }
}