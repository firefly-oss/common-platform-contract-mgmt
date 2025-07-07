package com.catalis.core.contract.web.controllers.contract.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.core.services.contract.v1.ContractServiceImpl;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractDTO;
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

@Tag(name = "Contracts", description = "APIs for managing contract records")
@RestController
@RequestMapping("/api/v1/contracts")
public class ContractController {

    @Autowired
    private ContractServiceImpl service;

    @Operation(
            summary = "Filter Contracts",
            description = "Apply filters to retrieve a paginated list of contracts."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved filtered contracts"),
            @ApiResponse(responseCode = "404", description = "No results found", content = @Content)
    })
    @PostMapping(value = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<ContractDTO>>> filterContracts(
            @RequestBody FilterRequest<ContractDTO> filterRequest
    ) {
        return service.filterContracts(filterRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Contract",
            description = "Create a new contract record."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contract created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid contract data provided", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractDTO>> createContract(
            @Parameter(description = "Contract data to be created", required = true,
                    schema = @Schema(implementation = ContractDTO.class))
            @RequestBody ContractDTO dto
    ) {
        return service.createContract(dto)
                .map(created -> ResponseEntity.status(HttpStatus.CREATED).body(created))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Contract by ID",
            description = "Retrieve a specific contract by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the contract",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractDTO.class))),
            @ApiResponse(responseCode = "404", description = "Contract not found", content = @Content)
    })
    @GetMapping(value = "/{contractId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractDTO>> getContract(
            @Parameter(description = "Unique identifier of the contract to retrieve", required = true)
            @PathVariable Long contractId
    ) {
        return service.getContract(contractId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Contract",
            description = "Update an existing contract by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contract updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractDTO.class))),
            @ApiResponse(responseCode = "404", description = "Contract not found", content = @Content)
    })
    @PutMapping(value = "/{contractId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractDTO>> updateContract(
            @Parameter(description = "Unique identifier of the contract to update", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Updated contract data", required = true,
                    schema = @Schema(implementation = ContractDTO.class))
            @RequestBody ContractDTO dto
    ) {
        return service.updateContract(contractId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Contract",
            description = "Remove a specific contract record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Contract deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contract not found", content = @Content)
    })
    @DeleteMapping("/{contractId}")
    public Mono<ResponseEntity<Void>> deleteContract(
            @Parameter(description = "Unique identifier of the contract to delete", required = true)
            @PathVariable Long contractId
    ) {
        return service.deleteContract(contractId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}