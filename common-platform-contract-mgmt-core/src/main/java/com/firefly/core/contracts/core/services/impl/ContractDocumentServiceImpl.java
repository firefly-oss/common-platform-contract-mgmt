package com.firefly.core.contracts.core.services.impl;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.core.mappers.ContractDocumentMapper;
import com.firefly.core.contracts.core.services.ContractDocumentService;
import com.firefly.core.contracts.interfaces.dtos.ContractDocumentDTO;
import com.firefly.core.contracts.models.entities.ContractDocument;
import com.firefly.core.contracts.models.repositories.ContractDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
@Transactional
public class ContractDocumentServiceImpl implements ContractDocumentService {

    @Autowired
    private ContractDocumentRepository repository;

    @Autowired
    private ContractDocumentMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractDocumentDTO>> filterContractDocuments(FilterRequest<ContractDocumentDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        ContractDocument.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<ContractDocumentDTO> createContractDocument(ContractDocumentDTO contractDocumentDTO) {
        return Mono.just(contractDocumentDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ContractDocumentDTO> updateContractDocument(UUID contractDocumentId, ContractDocumentDTO contractDocumentDTO) {
        return repository.findById(contractDocumentId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract document not found with ID: " + contractDocumentId)))
                .flatMap(existingDocument -> {
                    ContractDocument updatedDocument = mapper.toEntity(contractDocumentDTO);
                    updatedDocument.setContractDocumentId(contractDocumentId);
                    return repository.save(updatedDocument);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteContractDocument(UUID contractDocumentId) {
        return repository.findById(contractDocumentId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract document not found with ID: " + contractDocumentId)))
                .flatMap(document -> repository.deleteById(contractDocumentId));
    }

    @Override
    public Mono<ContractDocumentDTO> getContractDocumentById(UUID contractDocumentId) {
        return repository.findById(contractDocumentId)
                .switchIfEmpty(Mono.error(new RuntimeException("Contract document not found with ID: " + contractDocumentId)))
                .map(mapper::toDTO);
    }
}
