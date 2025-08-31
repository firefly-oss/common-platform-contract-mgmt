package com.firefly.core.contracts.web.controllers;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.core.services.ContractTermDynamicService;
import com.firefly.core.contracts.interfaces.dtos.ContractTermDynamicDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/contracts/{contractId}/terms")
@Tag(name = "Contract Terms", description = "API for managing contract term dynamics")
@RequiredArgsConstructor
public class ContractTermDynamicController {

    private final ContractTermDynamicService contractTermDynamicService;

    @Operation(summary = "Filter contract terms", description = "Returns a paginated list of contract terms based on filter criteria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved contract terms",
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = PaginationResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid filter criteria provided", 
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
                content = @Content)
    })
    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<PaginationResponse<ContractTermDynamicDTO>>> filterContractTerms(
            @Parameter(description = "ID of the contract", required = true)
            @PathVariable Long contractId,
            @Valid @RequestBody FilterRequest<ContractTermDynamicDTO> filterRequest) {
        return ResponseEntity.ok(contractTermDynamicService.filterContractTermDynamics(filterRequest));
    }

    @Operation(summary = "Create a new contract term", description = "Creates a new contract term with the provided information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contract term successfully created",
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ContractTermDynamicDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid contract term data provided", 
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Contract not found", 
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
                content = @Content)
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<ContractTermDynamicDTO>> createContractTerm(
            @Parameter(description = "ID of the contract", required = true)
            @PathVariable Long contractId,
            @Valid @RequestBody ContractTermDynamicDTO contractTermDynamicDTO) {
        // Ensure the contractId in the path is used
        contractTermDynamicDTO.setContractId(contractId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contractTermDynamicService.createContractTermDynamic(contractTermDynamicDTO));
    }

    @Operation(summary = "Get contract term by ID", description = "Returns a contract term based on its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved contract term",
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ContractTermDynamicDTO.class))),
        @ApiResponse(responseCode = "404", description = "Contract term not found", 
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
                content = @Content)
    })
    @GetMapping(value = "/{termId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<ContractTermDynamicDTO>> getContractTermById(
            @Parameter(description = "ID of the contract", required = true)
            @PathVariable Long contractId,
            @Parameter(description = "ID of the contract term to retrieve", required = true)
            @PathVariable Long termId) {
        return ResponseEntity.ok(contractTermDynamicService.getContractTermDynamicById(termId)
                .filter(term -> term.getContractId().equals(contractId)));
    }

    @Operation(summary = "Update contract term", description = "Updates an existing contract term with the provided information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contract term successfully updated",
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ContractTermDynamicDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid contract term data provided", 
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Contract term not found", 
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
                content = @Content)
    })
    @PutMapping(value = "/{termId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<ContractTermDynamicDTO>> updateContractTerm(
            @Parameter(description = "ID of the contract", required = true)
            @PathVariable Long contractId,
            @Parameter(description = "ID of the contract term to update", required = true)
            @PathVariable Long termId,
            @Valid @RequestBody ContractTermDynamicDTO contractTermDynamicDTO) {
        // Ensure the contractId in the path is used
        contractTermDynamicDTO.setContractId(contractId);
        return ResponseEntity.ok(contractTermDynamicService.updateContractTermDynamic(termId, contractTermDynamicDTO));
    }

    @Operation(summary = "Delete contract term", description = "Deletes a contract term based on its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Contract term successfully deleted",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Contract term not found", 
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
                content = @Content)
    })
    @DeleteMapping("/{termId}")
    public Mono<ResponseEntity<Void>> deleteContractTerm(
            @Parameter(description = "ID of the contract", required = true)
            @PathVariable Long contractId,
            @Parameter(description = "ID of the contract term to delete", required = true)
            @PathVariable Long termId) {
        return contractTermDynamicService.getContractTermDynamicById(termId)
                .filter(term -> term.getContractId().equals(contractId))
                .flatMap(term -> contractTermDynamicService.deleteContractTermDynamic(termId))
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}
