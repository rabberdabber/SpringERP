package com.codebake.springerp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BusinessPartnerService {
    
    private final BusinessPartnerRepository businessPartnerRepository;
    private final OrganizationRepository organizationRepository;
    
    public BusinessPartner create(BusinessPartner businessPartner) {
        validateOrganization(businessPartner);
        return businessPartnerRepository.save(businessPartner);
    }
    
    @Transactional(readOnly = true)
    public Optional<BusinessPartner> findById(Integer id) {
        return businessPartnerRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<BusinessPartner> findAll() {
        return businessPartnerRepository.findAll();
    }
    
    public BusinessPartner update(Integer id, BusinessPartner updatedBusinessPartner) {
        return businessPartnerRepository.findById(id)
                .map(businessPartner -> {
                    businessPartner.setName(updatedBusinessPartner.getName());
                    if (updatedBusinessPartner.getOrganization() != null) {
                        validateOrganization(updatedBusinessPartner);
                        businessPartner.setOrganization(updatedBusinessPartner.getOrganization());
                    }
                    return businessPartnerRepository.save(businessPartner);
                })
                .orElseThrow(() -> new RuntimeException("Business Partner not found with id: " + id));
    }
    
    public void deleteById(Integer id) {
        businessPartnerRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return businessPartnerRepository.existsById(id);
    }
    
    @Transactional(readOnly = true)
    public List<BusinessPartner> findByOrganizationId(Integer organizationId) {
        return businessPartnerRepository.findByOrganizationId(organizationId);
    }
    
    @Transactional(readOnly = true)
    public List<BusinessPartner> searchByName(String name) {
        return businessPartnerRepository.findByNameContainingIgnoreCase(name);
    }
    
    @Transactional(readOnly = true)
    public List<BusinessPartner> searchByOrganizationAndName(Integer organizationId, String name) {
        return businessPartnerRepository.findByOrganizationIdAndNameContaining(organizationId, name);
    }
    
    @Transactional(readOnly = true)
    public long countByOrganization(Integer organizationId) {
        return businessPartnerRepository.findByOrganizationId(organizationId).size();
    }
    
    private void validateOrganization(BusinessPartner businessPartner) {
        if (businessPartner.getOrganization() != null && 
            businessPartner.getOrganization().getId() != null) {
            organizationRepository.findById(businessPartner.getOrganization().getId())
                    .orElseThrow(() -> new RuntimeException("Organization not found with id: " + 
                                                           businessPartner.getOrganization().getId()));
        }
    }
}