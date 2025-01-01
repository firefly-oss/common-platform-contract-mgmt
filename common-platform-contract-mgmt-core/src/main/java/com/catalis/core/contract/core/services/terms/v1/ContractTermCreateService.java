package com.catalis.core.contract.core.services.terms.v1;

import com.catalis.core.contract.core.mappers.terms.v1.ContractTermMapper;
import com.catalis.core.contract.interfaces.dtos.terms.v1.ContractTermDTO;
import com.catalis.core.contract.models.repositories.terms.v1.ContractTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractTermCreateService {

    @Autowired
    private ContractTermRepository repository;

    @Autowired
    private ContractTermMapper mapper;

    /**
     * Creates a new contract term based on the provided {@link ContractTermDTO}.
     * The method maps the DTO to an entity, saves it to the repository, and returns the saved entity as a DTO.
     *
     * @param contractTerm the {@link ContractTermDTO} containing the details of the contract term to create
     * @return a {@link Mono} emitting the saved {@link ContractTermDTO}
     */
    public Mono<ContractTermDTO> createContractTerm(ContractTermDTO contractTerm) {
        return Mono.just(contractTerm)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(savedEntity -> {
                    ContractTermDTO dto = mapper.toDto(savedEntity);
                    dto.setContractTermId(savedEntity.getContractTermId());
                    return dto;
                });
    }

}
