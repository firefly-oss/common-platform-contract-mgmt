package com.catalis.core.contract.web.controllers.contract.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.core.services.contract.v1.ContractStatusHistoryServiceImpl;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractStatusHistoryDTO;
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

@Tag(name = "Contract Status History", description = "APIs for managing contract status changes over time")
@RestController
@RequestMapping("/api/v1/contracts/{contractId}/status-history")
public class ContractStatusHistoryController {

    @Autowired
    private ContractStatusHistoryServiceImpl service;


    @Operation(
            summary = "Retrieve Contract Status History",
            description = "Retrieves a paginated list of status history records for the specified contract."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved contract status history",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No status history found for the specified contract",
                    content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<ContractStatusHistoryDTO>>> getAllStatuses(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.getAllStatuses(contractId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Contract Status History",
            description = "Creates a new status history record for the specified contract."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Status history record created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractStatusHistoryDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid status history data provided", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractStatusHistoryDTO>> createStatusHistory(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Status history data to be created", required = true,
                    schema = @Schema(implementation = ContractStatusHistoryDTO.class))
            @RequestBody ContractStatusHistoryDTO dto
    ) {
        return service.createStatusHistory(contractId, dto)
                .map(created -> ResponseEntity.status(HttpStatus.CREATED).body(created))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Contract Status History",
            description = "Retrieve a specific status history record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the status history record",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractStatusHistoryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Status history record not found", content = @Content)
    })
    @GetMapping(value = "/{historyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractStatusHistoryDTO>> getStatusHistory(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the history record", required = true)
            @PathVariable Long historyId
    ) {
        return service.getStatusHistory(contractId, historyId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Contract Status History",
            description = "Update an existing status history record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status history record updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractStatusHistoryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Status history record not found", content = @Content)
    })
    @PutMapping(value = "/{historyId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractStatusHistoryDTO>> updateStatusHistory(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the history record to update", required = true)
            @PathVariable Long historyId,

            @Parameter(description = "Updated status history data", required = true,
                    schema = @Schema(implementation = ContractStatusHistoryDTO.class))
            @RequestBody ContractStatusHistoryDTO dto
    ) {
        return service.updateStatusHistory(contractId, historyId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Contract Status History",
            description = "Remove an existing status history record from the contract by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Status history record deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Status history record not found", content = @Content)
    })
    @DeleteMapping("/{historyId}")
    public Mono<ResponseEntity<Void>> deleteStatusHistory(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the history record to delete", required = true)
            @PathVariable Long historyId
    ) {
        return service.deleteStatusHistory(contractId, historyId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
