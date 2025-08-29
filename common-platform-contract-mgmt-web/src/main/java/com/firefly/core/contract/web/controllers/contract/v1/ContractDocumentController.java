package com.firefly.core.contract.web.controllers.contract.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contract.core.services.contract.v1.ContractDocumentServiceImpl;
import com.firefly.core.contract.interfaces.dtos.contract.v1.ContractDocumentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "Contract Documents", description = "APIs for managing contract documents")
@RestController
@RequestMapping("/api/v1/contracts/{contractId}/documents")
public class ContractDocumentController {

    @Autowired
    private ContractDocumentServiceImpl service;

    @Operation(
            summary = "Retrieve Contract Documents",
            description = "Retrieves a paginated list of contract documents for the specified contract."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved contract documents",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No documents found", content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<ContractDocumentDTO>>> getAllDocuments(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.getAllDocuments(contractId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Contract Document",
            description = "Create a new contract document for the specified contract."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Document created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractDocumentDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid contract document data", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractDocumentDTO>> createDocument(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Contract document data to be created", required = true,
                    schema = @Schema(implementation = ContractDocumentDTO.class))
            @RequestBody ContractDocumentDTO dto
    ) {
        return service.createDocument(contractId, dto)
                .map(createdDoc -> ResponseEntity.status(201).body(createdDoc))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Retrieve Contract Document",
            description = "Retrieve an existing contract document by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the contract document",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractDocumentDTO.class))),
            @ApiResponse(responseCode = "404", description = "Document not found", content = @Content)
    })
    @GetMapping(value = "/{documentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractDocumentDTO>> getDocument(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the document", required = true)
            @PathVariable Long documentId
    ) {
        return service.getDocument(contractId, documentId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Contract Document",
            description = "Update an existing contract document by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Document updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractDocumentDTO.class))),
            @ApiResponse(responseCode = "404", description = "Document not found", content = @Content)
    })
    @PutMapping(value = "/{documentId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractDocumentDTO>> updateDocument(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the document", required = true)
            @PathVariable Long documentId,

            @Parameter(description = "Updated contract document data", required = true,
                    schema = @Schema(implementation = ContractDocumentDTO.class))
            @RequestBody ContractDocumentDTO dto
    ) {
        return service.updateDocument(contractId, documentId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Contract Document",
            description = "Remove an existing contract document by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Document deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Document not found", content = @Content)
    })
    @DeleteMapping(value = "/{documentId}")
    public Mono<ResponseEntity<Void>> deleteDocument(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the document", required = true)
            @PathVariable Long documentId
    ) {
        return service.deleteDocument(contractId, documentId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}