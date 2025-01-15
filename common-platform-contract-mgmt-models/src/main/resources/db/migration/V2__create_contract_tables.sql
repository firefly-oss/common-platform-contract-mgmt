-- V2__create_contract_tables.sql
-- Contract Table
CREATE TABLE contract (
                          contract_id BIGSERIAL PRIMARY KEY,
                          contract_number VARCHAR(50) NOT NULL UNIQUE,
                          product_id BIGINT NOT NULL,
                          contract_type contract_type NOT NULL,
                          contract_status contract_status NOT NULL,
                          start_date TIMESTAMP NOT NULL,
                          end_date TIMESTAMP,
                          document_manager_ref VARCHAR(100),
                          date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Contract Party Table
CREATE TABLE contract_party (
                                contract_party_id BIGSERIAL PRIMARY KEY,
                                contract_id BIGINT NOT NULL,
                                party_id BIGINT NOT NULL,
                                role_in_contract role_in_contract NOT NULL,
                                date_joined TIMESTAMP NOT NULL,
                                date_left TIMESTAMP,
                                is_active BOOLEAN NOT NULL DEFAULT true,
                                date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                CONSTRAINT fk_contract_party_contract
                                    FOREIGN KEY (contract_id)
                                        REFERENCES contract(contract_id)
);

-- Contract Document Table
CREATE TABLE contract_document (
                                   contract_document_id BIGSERIAL PRIMARY KEY,
                                   contract_id BIGINT NOT NULL,
                                   document_type VARCHAR(50) NOT NULL,
                                   document_manager_ref VARCHAR(100) NOT NULL,
                                   date_added TIMESTAMP NOT NULL,
                                   date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   CONSTRAINT fk_contract_document_contract
                                       FOREIGN KEY (contract_id)
                                           REFERENCES contract(contract_id)
);

-- Contract Status History Table
CREATE TABLE contract_status_history (
                                         contract_status_history_id BIGSERIAL PRIMARY KEY,
                                         contract_id BIGINT NOT NULL,
                                         status_code status_code NOT NULL,
                                         status_start_date TIMESTAMP NOT NULL,
                                         status_end_date TIMESTAMP,
                                         date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         CONSTRAINT fk_contract_status_history_contract
                                             FOREIGN KEY (contract_id)
                                                 REFERENCES contract(contract_id)
);


-- Contract Term Table
CREATE TABLE contract_term (
                               contract_term_id BIGSERIAL PRIMARY KEY,
                               contract_id BIGINT NOT NULL,
                               term_type term_type NOT NULL,
                               term_description TEXT NOT NULL,
                               numeric_value DECIMAL(19,4) NOT NULL,
                               value_unit VARCHAR(20) NOT NULL,
                               effective_date TIMESTAMP NOT NULL,
                               expiration_date TIMESTAMP,
                               date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT fk_contract_term_contract
                                   FOREIGN KEY (contract_id)
                                       REFERENCES contract(contract_id)
);

-- Contract Event Table
CREATE TABLE contract_event (
                                contract_event_id BIGSERIAL PRIMARY KEY,
                                contract_id BIGINT NOT NULL,
                                event_type event_type NOT NULL,
                                event_date TIMESTAMP NOT NULL,
                                event_description TEXT,
                                document_manager_ref VARCHAR(100),
                                date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                CONSTRAINT fk_contract_event_contract
                                    FOREIGN KEY (contract_id)
                                        REFERENCES contract(contract_id)
);

-- Contract Risk Assessment Table
CREATE TABLE contract_risk_assessment (
                                          contract_risk_assessment_id BIGSERIAL PRIMARY KEY,
                                          contract_id BIGINT NOT NULL,
                                          risk_score DECIMAL(5,2) NOT NULL,
                                          risk_level risk_level NOT NULL,
                                          assessment_date TIMESTAMP NOT NULL,
                                          assessor VARCHAR(100) NOT NULL,
                                          notes TEXT,
                                          date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                          date_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                          CONSTRAINT fk_contract_risk_assessment_contract
                                              FOREIGN KEY (contract_id)
                                                  REFERENCES contract(contract_id)
);