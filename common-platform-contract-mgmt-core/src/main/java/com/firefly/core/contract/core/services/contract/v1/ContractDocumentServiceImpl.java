package com.firefly.core.contract.core.services.contract.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.contract.core.mappers.contract.v1.ContractDocumentMapper;
import com.firefly.core.contract.interfaces.dtos.contract.v1.ContractDocumentDTO;
import com.firefly.core.contract.models.entities.contract.v1.ContractDocument;
import com.firefly.core.contract.models.repositories.contract.v1.ContractDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ContractDocumentServiceImpl implements ContractDocumentService {

    @Autowired
    private ContractDocumentRepository repository;

    @Autowired
    private ContractDocumentMapper mapper;

    @Override
    public Mono<PaginationResponse<ContractDocumentDTO>> getAllDocuments(Long contractId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDto,
                pageable -> repository.findByContractId(contractId, pageable),
                () -> repository.countByContractId(contractId)
        );
    }

    @Override
    public Mono<ContractDocumentDTO> createDocument(Long contractId, ContractDocumentDTO dto) {
        ContractDocument entity = mapper.toEntity(dto);
        entity.setContractId(contractId);
        return repository.save(entity).map(mapper::toDto);
    }

    @Override
    public Mono<ContractDocumentDTO> getDocument(Long contractId, Long documentId) {
        return repository.findById(documentId)
                .filter(doc -> doc.getContractId().equals(contractId))
                .map(mapper::toDto);
    }

    @Override
    public Mono<ContractDocumentDTO> updateDocument(Long contractId, Long documentId, ContractDocumentDTO dto) {
        return repository.findById(documentId)
                .filter(doc -> doc.getContractId().equals(contractId))
                .flatMap(existingDoc -> {
                    ContractDocument updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setContractDocumentId(existingDoc.getContractDocumentId());
                    updatedEntity.setContractId(contractId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> deleteDocument(Long contractId, Long documentId) {
        return repository.findById(documentId)
                .filter(doc -> doc.getContractId().equals(contractId))
                .flatMap(repository::delete);
    }
}
