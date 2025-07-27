package com.codebake.springerp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessPartnerRepository extends JpaRepository<BusinessPartner, Integer> {
    
    List<BusinessPartner> findByOrganizationId(Integer organizationId);
    
    List<BusinessPartner> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT bp FROM BusinessPartner bp WHERE bp.organization.id = :orgId AND LOWER(bp.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<BusinessPartner> findByOrganizationIdAndNameContaining(@Param("orgId") Integer organizationId, @Param("name") String name);
}