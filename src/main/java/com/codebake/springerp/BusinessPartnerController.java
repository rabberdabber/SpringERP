package com.codebake.springerp;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/business-partners")
@RequiredArgsConstructor
@Tag(name = "Business Partner", description = "Business Partner management APIs")
public class BusinessPartnerController {
    
    private final BusinessPartnerService businessPartnerService;
    
    @PostMapping
    @Operation(summary = "Create a new business partner", description = "Creates a new business partner with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Business partner created successfully",
                content = @Content(schema = @Schema(implementation = BusinessPartner.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input or organization not found")
    })
    public ResponseEntity<BusinessPartner> createBusinessPartner(@Valid @RequestBody BusinessPartner businessPartner) {
        try {
            BusinessPartner created = businessPartnerService.create(businessPartner);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get business partner by ID", description = "Returns a single business partner")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Business partner found",
                content = @Content(schema = @Schema(implementation = BusinessPartner.class))),
        @ApiResponse(responseCode = "404", description = "Business partner not found")
    })
    public ResponseEntity<BusinessPartner> getBusinessPartnerById(
            @Parameter(description = "ID of business partner to return") @PathVariable Integer id) {
        return businessPartnerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    @Operation(summary = "Get all business partners", description = "Returns a list of all business partners")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = BusinessPartner.class)))
    public List<BusinessPartner> getAllBusinessPartners() {
        return businessPartnerService.findAll();
    }
    
    @GetMapping("/organization/{organizationId}")
    @Operation(summary = "Get business partners by organization", description = "Returns all business partners for a specific organization")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = BusinessPartner.class)))
    public List<BusinessPartner> getBusinessPartnersByOrganization(
            @Parameter(description = "ID of organization") @PathVariable Integer organizationId) {
        return businessPartnerService.findByOrganizationId(organizationId);
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search business partners", description = "Search business partners by name and optionally by organization")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = BusinessPartner.class)))
    public List<BusinessPartner> searchBusinessPartners(
            @Parameter(description = "Name to search for") @RequestParam String name,
            @Parameter(description = "Organization ID (optional)") @RequestParam(required = false) Integer organizationId) {
        if (organizationId != null) {
            return businessPartnerService.searchByOrganizationAndName(organizationId, name);
        }
        return businessPartnerService.searchByName(name);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing business partner", description = "Updates a business partner with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Business partner updated successfully",
                content = @Content(schema = @Schema(implementation = BusinessPartner.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Business partner not found")
    })
    public ResponseEntity<BusinessPartner> updateBusinessPartner(
            @Parameter(description = "ID of business partner to update") @PathVariable Integer id,
            @Valid @RequestBody BusinessPartner businessPartner) {
        try {
            BusinessPartner updated = businessPartnerService.update(id, businessPartner);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a business partner", description = "Deletes a business partner by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Business partner deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Business partner not found")
    })
    public ResponseEntity<Void> deleteBusinessPartner(
            @Parameter(description = "ID of business partner to delete") @PathVariable Integer id) {
        if (businessPartnerService.existsById(id)) {
            businessPartnerService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/organization/{organizationId}/count")
    @Operation(summary = "Count business partners by organization", description = "Returns the count of business partners for a specific organization")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public long countBusinessPartnersByOrganization(
            @Parameter(description = "ID of organization") @PathVariable Integer organizationId) {
        return businessPartnerService.countByOrganization(organizationId);
    }
}