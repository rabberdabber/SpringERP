-- SpringERP Seed Data Migration Script
-- File: V3__seed_data.sql (or adjust version number based on your existing migrations)
-- Description: Populates Organization and Business Partner tables with sample data

-- Clear existing data (optional - remove if you want to preserve existing data)
-- DELETE FROM business_partner;
-- DELETE FROM organization;

-- Reset sequences (optional - uncomment if clearing data above)
-- ALTER SEQUENCE organization_id_seq RESTART WITH 1;
-- ALTER SEQUENCE business_partner_id_seq RESTART WITH 1;

-- Insert Organizations
INSERT INTO organization (name, domain, created_at) VALUES
                                                        ('TechCorp Solutions', 'techcorp.com', CURRENT_TIMESTAMP - INTERVAL '365 days'),
                                                        ('Global Manufacturing Inc', 'globalmanuf.com', CURRENT_TIMESTAMP - INTERVAL '280 days'),
                                                        ('Digital Services Ltd', 'digitalservices.co.uk', CURRENT_TIMESTAMP - INTERVAL '180 days'),
                                                        ('Innovative Startups Hub', 'innovhub.io', CURRENT_TIMESTAMP - INTERVAL '120 days'),
                                                        ('Enterprise Systems Group', 'enterprisesys.net', CURRENT_TIMESTAMP - INTERVAL '90 days'),
                                                        ('Cloud Solutions Pro', 'cloudsolutions.cloud', CURRENT_TIMESTAMP - INTERVAL '60 days'),
                                                        ('Data Analytics Corp', 'dataanalytics.ai', CURRENT_TIMESTAMP - INTERVAL '45 days'),
                                                        ('Mobile First Technologies', 'mobilefirst.app', CURRENT_TIMESTAMP - INTERVAL '30 days'),
                                                        ('Sustainable Energy Co', 'sustainenergy.green', CURRENT_TIMESTAMP - INTERVAL '15 days'),
                                                        ('Future Logistics Ltd', 'futurelogistics.com', CURRENT_TIMESTAMP - INTERVAL '7 days');

-- Insert Business Partners
-- Partners for TechCorp Solutions (org_id: 1)
INSERT INTO business_partner (name, organization_id, created_at) VALUES
                                                                     ('Microsoft Corporation', 1, CURRENT_TIMESTAMP - INTERVAL '300 days'),
                                                                     ('Amazon Web Services', 1, CURRENT_TIMESTAMP - INTERVAL '250 days'),
                                                                     ('Salesforce Inc', 1, CURRENT_TIMESTAMP - INTERVAL '200 days'),
                                                                     ('Oracle Systems', 1, CURRENT_TIMESTAMP - INTERVAL '150 days'),
                                                                     ('IBM Cloud Services', 1, CURRENT_TIMESTAMP - INTERVAL '100 days');

-- Partners for Global Manufacturing Inc (org_id: 2)
INSERT INTO business_partner (name, organization_id, created_at) VALUES
                                                                     ('Steel Works International', 2, CURRENT_TIMESTAMP - INTERVAL '275 days'),
                                                                     ('Precision Tools Ltd', 2, CURRENT_TIMESTAMP - INTERVAL '225 days'),
                                                                     ('Industrial Equipment Co', 2, CURRENT_TIMESTAMP - INTERVAL '175 days'),
                                                                     ('Quality Control Systems', 2, CURRENT_TIMESTAMP - INTERVAL '125 days'),
                                                                     ('Logistics Express', 2, CURRENT_TIMESTAMP - INTERVAL '75 days');

-- Partners for Digital Services Ltd (org_id: 3)
INSERT INTO business_partner (name, organization_id, created_at) VALUES
                                                                     ('Creative Design Studio', 3, CURRENT_TIMESTAMP - INTERVAL '170 days'),
                                                                     ('SEO Marketing Pro', 3, CURRENT_TIMESTAMP - INTERVAL '140 days'),
                                                                     ('Content Management Plus', 3, CURRENT_TIMESTAMP - INTERVAL '110 days'),
                                                                     ('Social Media Experts', 3, CURRENT_TIMESTAMP - INTERVAL '80 days');

-- Partners for Innovative Startups Hub (org_id: 4)
INSERT INTO business_partner (name, organization_id, created_at) VALUES
                                                                     ('Venture Capital Group', 4, CURRENT_TIMESTAMP - INTERVAL '115 days'),
                                                                     ('Angel Investors Network', 4, CURRENT_TIMESTAMP - INTERVAL '95 days'),
                                                                     ('Tech Incubator Space', 4, CURRENT_TIMESTAMP - INTERVAL '85 days'),
                                                                     ('Startup Legal Services', 4, CURRENT_TIMESTAMP - INTERVAL '65 days');

-- Partners for Enterprise Systems Group (org_id: 5)
INSERT INTO business_partner (name, organization_id, created_at) VALUES
                                                                     ('SAP Integration Partners', 5, CURRENT_TIMESTAMP - INTERVAL '85 days'),
                                                                     ('ERP Consulting Firm', 5, CURRENT_TIMESTAMP - INTERVAL '70 days'),
                                                                     ('System Integration Corp', 5, CURRENT_TIMESTAMP - INTERVAL '55 days'),
                                                                     ('Database Solutions Inc', 5, CURRENT_TIMESTAMP - INTERVAL '40 days');

-- Partners for Cloud Solutions Pro (org_id: 6)
INSERT INTO business_partner (name, organization_id, created_at) VALUES
                                                                     ('Google Cloud Platform', 6, CURRENT_TIMESTAMP - INTERVAL '55 days'),
                                                                     ('Microsoft Azure', 6, CURRENT_TIMESTAMP - INTERVAL '50 days'),
                                                                     ('CloudFlare Services', 6, CURRENT_TIMESTAMP - INTERVAL '35 days');

-- Partners for Data Analytics Corp (org_id: 7)
INSERT INTO business_partner (name, organization_id, created_at) VALUES
                                                                     ('Tableau Software', 7, CURRENT_TIMESTAMP - INTERVAL '40 days'),
                                                                     ('Power BI Specialists', 7, CURRENT_TIMESTAMP - INTERVAL '35 days'),
                                                                     ('Data Warehouse Solutions', 7, CURRENT_TIMESTAMP - INTERVAL '25 days');

-- Partners for Mobile First Technologies (org_id: 8)
INSERT INTO business_partner (name, organization_id, created_at) VALUES
                                                                     ('iOS Development Team', 8, CURRENT_TIMESTAMP - INTERVAL '25 days'),
                                                                     ('Android Experts Inc', 8, CURRENT_TIMESTAMP - INTERVAL '20 days'),
                                                                     ('React Native Specialists', 8, CURRENT_TIMESTAMP - INTERVAL '15 days');

-- Partners for Sustainable Energy Co (org_id: 9)
INSERT INTO business_partner (name, organization_id, created_at) VALUES
                                                                     ('Solar Panel Manufacturers', 9, CURRENT_TIMESTAMP - INTERVAL '12 days'),
                                                                     ('Wind Energy Solutions', 9, CURRENT_TIMESTAMP - INTERVAL '10 days'),
                                                                     ('Battery Technology Corp', 9, CURRENT_TIMESTAMP - INTERVAL '8 days');

-- Partners for Future Logistics Ltd (org_id: 10)
INSERT INTO business_partner (name, organization_id, created_at) VALUES
                                                                     ('Drone Delivery Systems', 10, CURRENT_TIMESTAMP - INTERVAL '5 days'),
                                                                     ('Smart Warehouse Tech', 10, CURRENT_TIMESTAMP - INTERVAL '3 days'),
                                                                     ('AI Route Optimization', 10, CURRENT_TIMESTAMP - INTERVAL '1 day');

-- Some independent business partners (no organization)
INSERT INTO business_partner (name, organization_id, created_at) VALUES
                                                                     ('Freelance Consultant John Smith', NULL, CURRENT_TIMESTAMP - INTERVAL '90 days'),
                                                                     ('Independent Software Developer', NULL, CURRENT_TIMESTAMP - INTERVAL '60 days'),
                                                                     ('Marketing Freelancer Pro', NULL, CURRENT_TIMESTAMP - INTERVAL '30 days'),
                                                                     ('Legal Advisor Independent', NULL, CURRENT_TIMESTAMP - INTERVAL '15 days');

-- Display summary of inserted data
SELECT
    'Organizations' as table_name,
    COUNT(*) as record_count
FROM organization
UNION ALL
SELECT
    'Business Partners' as table_name,
    COUNT(*) as record_count
FROM business_partner
UNION ALL
SELECT
    'Partners with Organization' as table_name,
    COUNT(*) as record_count
FROM business_partner
WHERE organization_id IS NOT NULL
UNION ALL
SELECT
    'Independent Partners' as table_name,
    COUNT(*) as record_count
FROM business_partner
WHERE organization_id IS NULL;

-- Verification queries (optional - can be removed in production)
-- Show organizations with their partner counts
SELECT
    o.id,
    o.name as organization_name,
    o.domain,
    COUNT(bp.id) as partner_count
FROM organization o
         LEFT JOIN business_partner bp ON o.id = bp.organization_id
GROUP BY o.id, o.name, o.domain
ORDER BY o.id;

-- Show all business partners with their organizations
SELECT
    bp.id,
    bp.name as partner_name,
    COALESCE(o.name, 'Independent') as organization_name,
    bp.created_at
FROM business_partner bp
         LEFT JOIN organization o ON bp.organization_id = o.id
ORDER BY bp.organization_id NULLS LAST, bp.id;