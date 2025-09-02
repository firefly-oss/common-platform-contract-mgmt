package com.firefly.core.contracts.web.controllers;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contracts.core.services.ContractPartyService;
import com.firefly.core.contracts.interfaces.dtos.ContractPartyDTO;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/contracts/{contractId}/parties")
@Tag(name = "Contract Parties", description = "API for managing parties linked to a contract")
@RequiredArgsConstructor
public class ContractPartyController {

    private final ContractPartyService contractPartyService;

    @Operation(summary = "Filter contract parties", description = "Returns a paginated list of contract parties based on filter criteria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved contract parties",
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = PaginationResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid filter criteria provided", 
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
                content = @Content)
    })
    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<PaginationResponse<ContractPartyDTO>>> filterContractParties(
            @Parameter(description = "ID of the contract", required = true)
            @PathVariable UUID contractId,
            @Valid @RequestBody FilterRequest<ContractPartyDTO> filterRequest) {
        return ResponseEntity.ok(contractPartyService.filterContractParties(filterRequest));
    }

    @Operation(summary = "Create a new contract party", description = "Creates a new contract party with the provided information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contract party successfully created",
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ContractPartyDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid contract party data provided", 
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Contract not found", 
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
                content = @Content)
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<ContractPartyDTO>> createContractParty(
            @Parameter(description = "ID of the contract", required = true)
            @PathVariable UUID contractId,
            @Valid @RequestBody ContractPartyDTO contractPartyDTO) {
        // Ensure the contractId in the path is used
        contractPartyDTO.setContractId(contractId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contractPartyService.createContractParty(contractPartyDTO));
    }

    @Operation(summary = "Get contract party by ID", description = "Returns a contract party based on its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved contract party",
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ContractPartyDTO.class))),
        @ApiResponse(responseCode = "404", description = "Contract party not found", 
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
                content = @Content)
    })
    @GetMapping(value = "/{contractPartyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<ContractPartyDTO>> getContractPartyById(
            @Parameter(description = "ID of the contract", required = true)
            @PathVariable UUID contractId,
            @Parameter(description = "ID of the contract party to retrieve", required = true)
            @PathVariable UUID contractPartyId) {
        return ResponseEntity.ok(contractPartyService.getContractPartyById(contractPartyId)
                .filter(party -> party.getContractId().equals(contractId)));
    }

    @Operation(summary = "Update contract party", description = "Updates an existing contract party with the provided information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contract party successfully updated",
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ContractPartyDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid contract party data provided", 
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Contract party not found", 
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
                content = @Content)
    })
    @PutMapping(value = "/{contractPartyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<ContractPartyDTO>> updateContractParty(
            @Parameter(description = "ID of the contract", required = true)
            @PathVariable UUID contractId,
            @Parameter(description = "ID of the contract party to update", required = true)
            @PathVariable UUID contractPartyId,
            @Valid @RequestBody ContractPartyDTO contractPartyDTO) {
        // Ensure the contractId in the path is used
        contractPartyDTO.setContractId(contractId);
        return ResponseEntity.ok(contractPartyService.updateContractParty(contractPartyId, contractPartyDTO));
    }

    @Operation(summary = "Delete contract party", description = "Deletes a contract party based on its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Contract party successfully deleted",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Contract party not found", 
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
                content = @Content)
    })
    @DeleteMapping("/{contractPartyId}")
    public Mono<ResponseEntity<Void>> deleteContractParty(
            @Parameter(description = "ID of the contract", required = true)
            @PathVariable UUID contractId,
            @Parameter(description = "ID of the contract party to delete", required = true)
            @PathVariable UUID contractPartyId) {
        return contractPartyService.getContractPartyById(contractPartyId)
                .filter(party -> party.getContractId().equals(contractId))
                .flatMap(party -> contractPartyService.deleteContractParty(contractPartyId))
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}
