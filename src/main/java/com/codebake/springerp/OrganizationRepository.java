package com.codebake.springerp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    
    Optional<Organization> findByName(String name);
    
    Optional<Organization> findByDomain(String domain);
    
    List<Organization> findByNameContainingIgnoreCase(String name);
    
    boolean existsByName(String name);
    
    boolean existsByDomain(String domain);
}