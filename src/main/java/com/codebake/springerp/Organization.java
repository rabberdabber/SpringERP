package com.codebake.springerp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "organization")
@Schema(description = "Organization entity")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Unique identifier", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotBlank(message = "Organization name is required")
    @Size(min = 2, max = 255, message = "Organization name must be between 2 and 255 characters")
    @Column(name = "name")
    @Schema(description = "Organization name", example = "Acme Corporation", required = true)
    private String name;

    @Pattern(regexp = "^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,}$", 
             message = "Invalid domain format")
    @Column(name = "domain")
    @Schema(description = "Organization domain", example = "acme.com")
    private String domain;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    @Schema(description = "Creation timestamp", accessMode = Schema.AccessMode.READ_ONLY)
    private Instant createdAt;

}