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
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
@Tag(name = "Organization", description = "Organization management APIs")
public class OrganizationController {
    
    private final OrganizationService organizationService;
    
    @PostMapping
    @Operation(summary = "Create a new organization", description = "Creates a new organization with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Organization created successfully",
                content = @Content(schema = @Schema(implementation = Organization.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "409", description = "Organization already exists")
    })
    public ResponseEntity<Organization> createOrganization(@Valid @RequestBody Organization organization) {
        try {
            Organization created = organizationService.createIfNotExists(organization);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get organization by ID", description = "Returns a single organization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Organization found",
                content = @Content(schema = @Schema(implementation = Organization.class))),
        @ApiResponse(responseCode = "404", description = "Organization not found")
    })
    public ResponseEntity<Organization> getOrganizationById(
            @Parameter(description = "ID of organization to return") @PathVariable Integer id) {
        return organizationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    @Operation(summary = "Get all organizations", description = "Returns a list of all organizations")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = Organization.class)))
    public List<Organization> getAllOrganizations() {
        return organizationService.findAll();
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search organizations by name", description = "Search organizations by partial name match")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = Organization.class)))
    public List<Organization> searchOrganizations(
            @Parameter(description = "Name to search for") @RequestParam String name) {
        return organizationService.searchByName(name);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing organization", description = "Updates an organization with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Organization updated successfully",
                content = @Content(schema = @Schema(implementation = Organization.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Organization not found")
    })
    public ResponseEntity<Organization> updateOrganization(
            @Parameter(description = "ID of organization to update") @PathVariable Integer id,
            @Valid @RequestBody Organization organization) {
        try {
            Organization updated = organizationService.update(id, organization);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an organization", description = "Deletes an organization by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Organization deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Organization not found")
    })
    public ResponseEntity<Void> deleteOrganization(
            @Parameter(description = "ID of organization to delete") @PathVariable Integer id) {
        if (organizationService.existsById(id)) {
            organizationService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/check-name")
    @Operation(summary = "Check if organization name is available", description = "Returns true if the name is available")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public boolean checkNameAvailability(@RequestParam String name) {
        return organizationService.isNameAvailable(name);
    }
    
    @GetMapping("/check-domain")
    @Operation(summary = "Check if organization domain is available", description = "Returns true if the domain is available")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public boolean checkDomainAvailability(@RequestParam String domain) {
        return organizationService.isDomainAvailable(domain);
    }
}