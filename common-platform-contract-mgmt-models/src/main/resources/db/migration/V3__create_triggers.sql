-- V3__create_triggers.sql
-- Update Timestamp Trigger Function
CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.date_updated = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ language 'plpgsql';

-- Create Update Triggers for All Tables
CREATE TRIGGER update_contract_timestamp
    BEFORE UPDATE ON contract
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER update_contract_party_timestamp
    BEFORE UPDATE ON contract_party
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER update_contract_document_timestamp
    BEFORE UPDATE ON contract_document
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER update_contract_status_history_timestamp
    BEFORE UPDATE ON contract_status_history
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER update_contract_term_timestamp
    BEFORE UPDATE ON contract_term
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER update_contract_event_timestamp
    BEFORE UPDATE ON contract_event
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER update_contract_risk_assessment_timestamp
    BEFORE UPDATE ON contract_risk_assessment
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();