package com.firefly.core.contract.web.controllers.terms.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contract.core.services.terms.v1.ContractTermServiceImpl;
import com.firefly.core.contract.interfaces.dtos.terms.v1.ContractTermDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "Contract Terms", description = "APIs for managing terms of a contract")
@RestController
@RequestMapping("/api/v1/contracts/{contractId}/terms")
public class ContractTermController {

    @Autowired
    private ContractTermServiceImpl service;

    @Operation(
            summary = "Retrieve Contract Terms",
            description = "Retrieves a paginated list of terms for the specified contract."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved contract terms",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No terms found for the specified contract",
                    content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<ContractTermDTO>>> getAllTerms(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.getAllTerms(contractId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Contract Term",
            description = "Creates a new term for the specified contract."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Term created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractTermDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid term data provided", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractTermDTO>> createTerm(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Term data to be created", required = true,
                    schema = @Schema(implementation = ContractTermDTO.class))
            @RequestBody ContractTermDTO dto
    ) {
        return service.createTerm(contractId, dto)
                .map(createdTerm -> ResponseEntity.status(HttpStatus.CREATED).body(createdTerm))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Contract Term",
            description = "Retrieve a specific contract term by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the contract term",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractTermDTO.class))),
            @ApiResponse(responseCode = "404", description = "Contract term not found", content = @Content)
    })
    @GetMapping(value = "/{termId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractTermDTO>> getTerm(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the term", required = true)
            @PathVariable Long termId
    ) {
        return service.getTerm(contractId, termId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Contract Term",
            description = "Update an existing contract term by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contract term updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractTermDTO.class))),
            @ApiResponse(responseCode = "404", description = "Contract term not found", content = @Content)
    })
    @PutMapping(value = "/{termId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractTermDTO>> updateTerm(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the term to update", required = true)
            @PathVariable Long termId,

            @Parameter(description = "Updated contract term data", required = true,
                    schema = @Schema(implementation = ContractTermDTO.class))
            @RequestBody ContractTermDTO dto
    ) {
        return service.updateTerm(contractId, termId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Contract Term",
            description = "Remove an existing term from the contract by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Term deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contract term not found", content = @Content)
    })
    @DeleteMapping("/{termId}")
    public Mono<ResponseEntity<Void>> deleteTerm(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the term to delete", required = true)
            @PathVariable Long termId
    ) {
        return service.deleteTerm(contractId, termId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
