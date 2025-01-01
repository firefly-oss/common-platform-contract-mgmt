-- V4__create_enum_casts.sql

-- Contract Type casts
CREATE CAST (varchar AS contract_type) WITH INOUT AS IMPLICIT;
CREATE CAST (contract_type AS varchar) WITH INOUT AS IMPLICIT;

-- Contract Status casts
CREATE CAST (varchar AS contract_status) WITH INOUT AS IMPLICIT;
CREATE CAST (contract_status AS varchar) WITH INOUT AS IMPLICIT;

-- Role In Contract casts
CREATE CAST (varchar AS role_in_contract) WITH INOUT AS IMPLICIT;
CREATE CAST (role_in_contract AS varchar) WITH INOUT AS IMPLICIT;

-- Status Code casts
CREATE CAST (varchar AS status_code) WITH INOUT AS IMPLICIT;
CREATE CAST (status_code AS varchar) WITH INOUT AS IMPLICIT;

-- Term Type casts
CREATE CAST (varchar AS term_type) WITH INOUT AS IMPLICIT;
CREATE CAST (term_type AS varchar) WITH INOUT AS IMPLICIT;

-- Event Type casts
CREATE CAST (varchar AS event_type) WITH INOUT AS IMPLICIT;
CREATE CAST (event_type AS varchar) WITH INOUT AS IMPLICIT;

-- Risk Level casts
CREATE CAST (varchar AS risk_level) WITH INOUT AS IMPLICIT;
CREATE CAST (risk_level AS varchar) WITH INOUT AS IMPLICIT;