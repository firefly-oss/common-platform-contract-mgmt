package com.firefly.core.contract.web.controllers.risk.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.contract.core.services.risk.v1.ContractRiskAssessmentServiceImpl;
import com.firefly.core.contract.interfaces.dtos.risk.v1.ContractRiskAssessmentDTO;
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

@Tag(name = "Contract Risk Assessments", description = "APIs for managing contract risk assessments")
@RestController
@RequestMapping("/api/v1/contracts/{contractId}/risk-assessments")
public class ContractRiskAssessmentController {

    @Autowired
    private ContractRiskAssessmentServiceImpl service;

    @Operation(
            summary = "Retrieve Contract Risk Assessments",
            description = "Retrieves a paginated list of risk assessments for the specified contract."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved risk assessments",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No risk assessments found for the specified contract",
                    content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<ContractRiskAssessmentDTO>>> getAllRiskAssessments(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.getAllRiskAssessments(contractId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Contract Risk Assessment",
            description = "Creates a new risk assessment for the specified contract."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Risk assessment created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractRiskAssessmentDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid risk assessment data provided", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractRiskAssessmentDTO>> createRiskAssessment(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Risk assessment data to be created", required = true,
                    schema = @Schema(implementation = ContractRiskAssessmentDTO.class))
            @RequestBody ContractRiskAssessmentDTO dto
    ) {
        return service.createRiskAssessment(contractId, dto)
                .map(createdRisk -> ResponseEntity.status(HttpStatus.CREATED).body(createdRisk))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Contract Risk Assessment",
            description = "Retrieve a specific risk assessment linked to the contract by risk ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the risk assessment",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractRiskAssessmentDTO.class))),
            @ApiResponse(responseCode = "404", description = "Risk assessment not found", content = @Content)
    })
    @GetMapping(value = "/{riskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractRiskAssessmentDTO>> getRiskAssessment(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the risk assessment", required = true)
            @PathVariable Long riskId
    ) {
        return service.getRiskAssessment(contractId, riskId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Contract Risk Assessment",
            description = "Update an existing risk assessment by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Risk assessment updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContractRiskAssessmentDTO.class))),
            @ApiResponse(responseCode = "404", description = "Risk assessment not found", content = @Content)
    })
    @PutMapping(value = "/{riskId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ContractRiskAssessmentDTO>> updateRiskAssessment(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the risk assessment to update", required = true)
            @PathVariable Long riskId,

            @Parameter(description = "Updated risk assessment data", required = true,
                    schema = @Schema(implementation = ContractRiskAssessmentDTO.class))
            @RequestBody ContractRiskAssessmentDTO dto
    ) {
        return service.updateRiskAssessment(contractId, riskId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Contract Risk Assessment",
            description = "Remove an existing risk assessment from the contract by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Risk assessment deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Risk assessment not found", content = @Content)
    })
    @DeleteMapping("/{riskId}")
    public Mono<ResponseEntity<Void>> deleteRiskAssessment(
            @Parameter(description = "Unique identifier of the contract", required = true)
            @PathVariable Long contractId,

            @Parameter(description = "Unique identifier of the risk assessment to delete", required = true)
            @PathVariable Long riskId
    ) {
        return service.deleteRiskAssessment(contractId, riskId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
