package com.codebake.springerp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationService {
    
    private final OrganizationRepository organizationRepository;
    
    public Organization create(Organization organization) {
        return organizationRepository.save(organization);
    }
    
    @Transactional(readOnly = true)
    public Optional<Organization> findById(Integer id) {
        return organizationRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }
    
    public Organization update(Integer id, Organization updatedOrganization) {
        return organizationRepository.findById(id)
                .map(organization -> {
                    organization.setName(updatedOrganization.getName());
                    organization.setDomain(updatedOrganization.getDomain());
                    return organizationRepository.save(organization);
                })
                .orElseThrow(() -> new RuntimeException("Organization not found with id: " + id));
    }
    
    public void deleteById(Integer id) {
        organizationRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return organizationRepository.existsById(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<Organization> findByName(String name) {
        return organizationRepository.findByName(name);
    }
    
    @Transactional(readOnly = true)
    public Optional<Organization> findByDomain(String domain) {
        return organizationRepository.findByDomain(domain);
    }
    
    @Transactional(readOnly = true)
    public List<Organization> searchByName(String name) {
        return organizationRepository.findByNameContainingIgnoreCase(name);
    }
    
    public Organization createIfNotExists(Organization organization) {
        if (organization.getName() != null && organizationRepository.existsByName(organization.getName())) {
            throw new RuntimeException("Organization already exists with name: " + organization.getName());
        }
        if (organization.getDomain() != null && organizationRepository.existsByDomain(organization.getDomain())) {
            throw new RuntimeException("Organization already exists with domain: " + organization.getDomain());
        }
        return organizationRepository.save(organization);
    }
    
    @Transactional(readOnly = true)
    public boolean isNameAvailable(String name) {
        return !organizationRepository.existsByName(name);
    }
    
    @Transactional(readOnly = true)
    public boolean isDomainAvailable(String domain) {
        return !organizationRepository.existsByDomain(domain);
    }
}