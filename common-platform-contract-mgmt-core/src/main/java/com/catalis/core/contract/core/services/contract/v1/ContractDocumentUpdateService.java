package com.catalis.core.contract.core.services.contract.v1;

import com.catalis.core.contract.core.mappers.contract.v1.ContractDocumentMapper;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractDocumentDTO;
import com.catalis.core.contract.models.entities.contract.v1.ContractDocument;
import com.catalis.core.contract.models.repositories.contract.v1.ContractDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractDocumentUpdateService {

    @Autowired
    private ContractDocumentRepository repository;

    @Autowired
    private ContractDocumentMapper mapper;

    /**
     * Updates an existing contract document identified by its ID with the details provided in the request.
     *
     * @param id the unique identifier of the contract document to be updated
     * @param request the data transfer object containing the updated information for the contract document
     * @return a Mono emitting the updated contract document data transfer object, or an error if the update fails or the document is not found
     */
    public Mono<ContractDocumentDTO> updateContractDocument(Long id, ContractDocumentDTO request) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Contract document with ID " + id + " not found")))
                .flatMap(existingDocument -> {
                    ContractDocument updatedEntity = mapper.toEntity(request);
                    updatedEntity.setContractId(request.getContractId());
                    updatedEntity.setDocumentType(request.getDocumentType());
                    updatedEntity.setDocumentManagerRef(request.getDocumentManagerRef());
                    updatedEntity.setDateAdded(request.getDateAdded());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDto)
                .onErrorResume(error -> Mono.error(new RuntimeException("Failed to update contract document: " + error.getMessage(), error)));
    }

}
