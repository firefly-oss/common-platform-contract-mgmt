package com.catalis.core.contract.web.controllers.contract.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.contract.core.services.contract.v1.ContractPartyServiceImpl;
import com.catalis.core.contract.interfaces.dtos.contract.v1.ContractPartyDTO;
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

@Tag(name = "Contract Parties", description = "APIs for managing parties linked to a contract")
@RestController
@RequestMapping("/api/v1/contracts/{contractId}/parties")
public class ContractPartyController {

    @Autowired
    private ContractPartyServiceImpl service;

    @Operation(
            summary = "Retrieve Contract Parties",
            description = "Retrieves a paginated list of parties for the specified contract."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved contract parties",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No parties found for the specified contract",
                    content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<ContractPartyDTO>>> getAllParties(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.getAllParties(contractId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Contract Party Link",
            description = "Creates a new party link for the specified contract."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Party link created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractPartyDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid party data provided", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractPartyDTO>> createPartyLink(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Party data to be linked", required = true,
                    schema = @Schema(implementation = ContractPartyDTO.class))
            @RequestBody ContractPartyDTO dto
    ) {
        return service.createPartyLink(contractId, dto)
                .map(createdParty -> ResponseEntity.status(201).body(createdParty))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Contract Party",
            description = "Retrieve a specific party linked to the contract by party ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the party link",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractPartyDTO.class))),
            @ApiResponse(responseCode = "404", description = "Party link not found", content = @Content)
    })
    @GetMapping(value = "/{partyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractPartyDTO>> getParty(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the party", required = true)
            @PathVariable Long partyId
    ) {
        return service.getParty(contractId, partyId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Contract Party",
            description = "Update an existing party link by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Party link updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractPartyDTO.class))),
            @ApiResponse(responseCode = "404", description = "Party link not found", content = @Content)
    })
    @PutMapping(value = "/{partyId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractPartyDTO>> updateParty(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the party to update", required = true)
            @PathVariable Long partyId,

            @Parameter(description = "Updated party data", required = true,
                    schema = @Schema(implementation = ContractPartyDTO.class))
            @RequestBody ContractPartyDTO dto
    ) {
        return service.updateParty(contractId, partyId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Contract Party",
            description = "Remove an existing party link from the contract by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Party link deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Party link not found", content = @Content)
    })
    @DeleteMapping("/{partyId}")
    public Mono<ResponseEntity<Void>> deleteParty(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the party to delete", required = true)
            @PathVariable Long partyId
    ) {
        return service.deleteParty(contractId, partyId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}