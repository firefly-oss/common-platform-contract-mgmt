-- V1__create_enums.sql
-- Contract Type Enum
CREATE TYPE contract_type AS ENUM (
    'ACCOUNT',
    'CARD',
    'LOAN',
    'MORTGAGE',
    'INVESTMENT'
);

-- Contract Status Enum
CREATE TYPE contract_status AS ENUM (
    'DRAFT',
    'PENDING',
    'ACTIVE',
    'SUSPENDED',
    'CLOSED',
    'CANCELLED'
);

-- Role In Contract Enum
CREATE TYPE role_in_contract AS ENUM (
    'PRIMARY_HOLDER',
    'JOINT_HOLDER',
    'GUARANTOR',
    'BENEFICIARY',
    'AUTHORIZED_USER'
);

-- Status Code Enum
CREATE TYPE status_code AS ENUM (
    'DRAFT',
    'ACTIVE',
    'DELINQUENT',
    'DEFAULT',
    'CLOSED',
    'CANCELLED'
);

-- Term Type Enum
CREATE TYPE term_type AS ENUM (
    'INTEREST_RATE',
    'CREDIT_LIMIT',
    'FEE_SCHEDULE',
    'PAYMENT_SCHEDULE',
    'PENALTY_RATE'
);

-- Event Type Enum
CREATE TYPE event_type AS ENUM (
    'RENEWAL',
    'AMENDMENT',
    'CANCELLATION',
    'SUSPENSION',
    'REACTIVATION',
    'TERMINATION'
);

-- Risk Level Enum
CREATE TYPE risk_level AS ENUM (
    'LOW',
    'MEDIUM',
    'HIGH',
    'CRITICAL'
);