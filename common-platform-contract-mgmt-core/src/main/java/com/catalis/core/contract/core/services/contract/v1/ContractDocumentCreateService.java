package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.core.contract.core.mappers.contract.v1.ContractDocumentMapper;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractDocumentDTO;
import com.catalis.core.contract.models.repositories.contract.v1.ContractDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractDocumentCreateService {

    @Autowired
    private ContractDocumentRepository repository;
    
    @Autowired
    private ContractDocumentMapper mapper;

    /**
     * Creates a new contract document based on the provided contract document data.
     *
     * @param request the contract document data transfer object containing the details of the contract document to be created
     * @return a Mono emitting the created contract document data transfer object
     */
    public Mono<ContractDocumentDTO> createContractDocument(ContractDocumentDTO request) {
        return Mono.just(request)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(savedEntity -> {
                    request.setContractDocumentId(savedEntity.getContractDocumentId());
                    return mapper.toDto(savedEntity);
                });
    }

}
