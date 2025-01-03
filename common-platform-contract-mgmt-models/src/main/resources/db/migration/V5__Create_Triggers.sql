-- V5__Create_Triggers.sql
-- Description: Creates triggers for timestamp management and business rules in the contract domain

-- Reusing timestamp functions if they don't exist (in case this runs before other migrations)
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_proc WHERE proname = 'update_timestamp') THEN
CREATE FUNCTION update_timestamp()
    RETURNS TRIGGER AS $func$
BEGIN
            NEW.date_updated = CURRENT_TIMESTAMP;
RETURN NEW;
END;
        $func$ LANGUAGE plpgsql;
END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_proc WHERE proname = 'set_initial_timestamps') THEN
CREATE FUNCTION set_initial_timestamps()
    RETURNS TRIGGER AS $func$
BEGIN
            NEW.date_created = CURRENT_TIMESTAMP;
            NEW.date_updated = CURRENT_TIMESTAMP;
RETURN NEW;
END;
        $func$ LANGUAGE plpgsql;
END IF;
END
$$;

-- === CONTRACT TABLE TRIGGERS ===
CREATE TRIGGER contract_timestamp_insert
    BEFORE INSERT ON contract
    FOR EACH ROW
    EXECUTE FUNCTION set_initial_timestamps();

CREATE TRIGGER contract_timestamp_update
    BEFORE UPDATE ON contract
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

-- Validate contract dates
CREATE OR REPLACE FUNCTION validate_contract_dates()
RETURNS TRIGGER AS $$
BEGIN
    -- End date must be after start date if specified
    IF NEW.end_date IS NOT NULL AND NEW.end_date <= NEW.start_date THEN
        RAISE EXCEPTION 'Contract end date must be after start date';
END IF;
RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER contract_dates_validation
    BEFORE INSERT OR UPDATE ON contract
                         FOR EACH ROW
                         EXECUTE FUNCTION validate_contract_dates();

-- === CONTRACT_PARTY TABLE TRIGGERS ===
CREATE TRIGGER contract_party_timestamp_insert
    BEFORE INSERT ON contract_party
    FOR EACH ROW
    EXECUTE FUNCTION set_initial_timestamps();

CREATE TRIGGER contract_party_timestamp_update
    BEFORE UPDATE ON contract_party
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

-- Validate contract party dates
CREATE OR REPLACE FUNCTION validate_contract_party_dates()
RETURNS TRIGGER AS $$
BEGIN
    -- Date left must be after date joined if specified
    IF NEW.date_left IS NOT NULL AND NEW.date_left <= NEW.date_joined THEN
        RAISE EXCEPTION 'Contract party leave date must be after join date';
END IF;

    -- Verify dates against contract dates
    DECLARE
contract_start_date date;
        contract_end_date date;
BEGIN
SELECT start_date, end_date
INTO contract_start_date, contract_end_date
FROM contract
WHERE contract_id = NEW.contract_id;

IF NEW.date_joined < contract_start_date THEN
            RAISE EXCEPTION 'Party join date cannot be before contract start date';
END IF;

        IF contract_end_date IS NOT NULL AND NEW.date_joined > contract_end_date THEN
            RAISE EXCEPTION 'Party join date cannot be after contract end date';
END IF;
END;

RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER contract_party_dates_validation
    BEFORE INSERT OR UPDATE ON contract_party
                         FOR EACH ROW
                         EXECUTE FUNCTION validate_contract_party_dates();

-- Ensure at least one active primary holder
CREATE OR REPLACE FUNCTION ensure_primary_holder()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'DELETE' OR NEW.is_active = FALSE THEN
        IF NOT EXISTS (
            SELECT 1 FROM contract_party
            WHERE contract_id = COALESCE(OLD.contract_id, NEW.contract_id)
            AND role_in_contract = 'PRIMARY_HOLDER'
            AND is_active = TRUE
            AND contract_party_id != COALESCE(OLD.contract_party_id, NEW.contract_party_id)
        ) THEN
            RAISE EXCEPTION 'Contract must have at least one active primary holder';
END IF;
END IF;
RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER ensure_contract_primary_holder
    AFTER DELETE OR UPDATE OF is_active ON contract_party
    FOR EACH ROW
    WHEN (OLD.role_in_contract = 'PRIMARY_HOLDER')
    EXECUTE FUNCTION ensure_primary_holder();

-- === CONTRACT_DOCUMENT TABLE TRIGGERS ===
CREATE TRIGGER contract_document_timestamp_insert
    BEFORE INSERT ON contract_document
    FOR EACH ROW
    EXECUTE FUNCTION set_initial_timestamps();

CREATE TRIGGER contract_document_timestamp_update
    BEFORE UPDATE ON contract_document
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

-- === CONTRACT_STATUS_HISTORY TABLE TRIGGERS ===
CREATE TRIGGER contract_status_history_timestamp_insert
    BEFORE INSERT ON contract_status_history
    FOR EACH ROW
    EXECUTE FUNCTION set_initial_timestamps();

CREATE TRIGGER contract_status_history_timestamp_update
    BEFORE UPDATE ON contract_status_history
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

-- Validate status history dates
CREATE OR REPLACE FUNCTION validate_status_history_dates()
RETURNS TRIGGER AS $$
BEGIN
    -- End date must be after start date if specified
    IF NEW.status_end_date IS NOT NULL AND NEW.status_end_date <= NEW.status_start_date THEN
        RAISE EXCEPTION 'Status end date must be after start date';
END IF;

    -- Check for overlapping status periods
    IF EXISTS (
        SELECT 1 FROM contract_status_history
        WHERE contract_id = NEW.contract_id
        AND contract_status_history_id != NEW.contract_status_history_id
        AND (
            (NEW.status_start_date BETWEEN status_start_date AND COALESCE(status_end_date, NEW.status_start_date))
            OR (COALESCE(NEW.status_end_date, status_start_date) BETWEEN status_start_date AND COALESCE(status_end_date, NEW.status_end_date))
        )
    ) THEN
        RAISE EXCEPTION 'Overlapping status periods are not allowed';
END IF;

RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER status_history_dates_validation
    BEFORE INSERT OR UPDATE ON contract_status_history
                         FOR EACH ROW
                         EXECUTE FUNCTION validate_status_history_dates();

-- === CONTRACT_TERM TABLE TRIGGERS ===
CREATE TRIGGER contract_term_timestamp_insert
    BEFORE INSERT ON contract_term
    FOR EACH ROW
    EXECUTE FUNCTION set_initial_timestamps();

CREATE TRIGGER contract_term_timestamp_update
    BEFORE UPDATE ON contract_term
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

-- Validate contract term dates
CREATE OR REPLACE FUNCTION validate_contract_term_dates()
RETURNS TRIGGER AS $$
BEGIN
    -- Expiration date must be after effective date if specified
    IF NEW.expiration_date IS NOT NULL AND NEW.expiration_date <= NEW.effective_date THEN
        RAISE EXCEPTION 'Term expiration date must be after effective date';
END IF;

    -- Verify dates against contract dates
    DECLARE
contract_start_date date;
        contract_end_date date;
BEGIN
SELECT start_date, end_date
INTO contract_start_date, contract_end_date
FROM contract
WHERE contract_id = NEW.contract_id;

IF NEW.effective_date < contract_start_date THEN
            RAISE EXCEPTION 'Term effective date cannot be before contract start date';
END IF;

        IF contract_end_date IS NOT NULL AND NEW.effective_date > contract_end_date THEN
            RAISE EXCEPTION 'Term effective date cannot be after contract end date';
END IF;
END;

RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER contract_term_dates_validation
    BEFORE INSERT OR UPDATE ON contract_term
                         FOR EACH ROW
                         EXECUTE FUNCTION validate_contract_term_dates();

-- === CONTRACT_EVENT TABLE TRIGGERS ===
CREATE TRIGGER contract_event_timestamp_insert
    BEFORE INSERT ON contract_event
    FOR EACH ROW
    EXECUTE FUNCTION set_initial_timestamps();

CREATE TRIGGER contract_event_timestamp_update
    BEFORE UPDATE ON contract_event
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

-- Validate contract event dates
CREATE OR REPLACE FUNCTION validate_contract_event_dates()
RETURNS TRIGGER AS $$
BEGIN
    -- Verify dates against contract dates
    DECLARE
contract_start_date date;
        contract_end_date date;
BEGIN
SELECT start_date, end_date
INTO contract_start_date, contract_end_date
FROM contract
WHERE contract_id = NEW.contract_id;

IF NEW.event_date < contract_start_date THEN
            RAISE EXCEPTION 'Event date cannot be before contract start date';
END IF;

        IF contract_end_date IS NOT NULL AND NEW.event_date > contract_end_date THEN
            RAISE EXCEPTION 'Event date cannot be after contract end date';
END IF;
END;

RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER contract_event_dates_validation
    BEFORE INSERT OR UPDATE ON contract_event
                         FOR EACH ROW
                         EXECUTE FUNCTION validate_contract_event_dates();

-- === CONTRACT_RISK_ASSESSMENT TABLE TRIGGERS ===
CREATE TRIGGER contract_risk_assessment_timestamp_insert
    BEFORE INSERT ON contract_risk_assessment
    FOR EACH ROW
    EXECUTE FUNCTION set_initial_timestamps();

CREATE TRIGGER contract_risk_assessment_timestamp_update
    BEFORE UPDATE ON contract_risk_assessment
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();

-- Validate risk assessment dates
CREATE OR REPLACE FUNCTION validate_risk_assessment_dates()
RETURNS TRIGGER AS $$
BEGIN
    -- Verify dates against contract dates
    DECLARE
contract_start_date date;
        contract_end_date date;
BEGIN
SELECT start_date, end_date
INTO contract_start_date, contract_end_date
FROM contract
WHERE contract_id = NEW.contract_id;

IF NEW.assessment_date < contract_start_date THEN
            RAISE EXCEPTION 'Assessment date cannot be before contract start date';
END IF;

        IF contract_end_date IS NOT NULL AND NEW.assessment_date > contract_end_date THEN
            RAISE EXCEPTION 'Assessment date cannot be after contract end date';
END IF;
END;

RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER risk_assessment_dates_validation
    BEFORE INSERT OR UPDATE ON contract_risk_assessment
                         FOR EACH ROW
                         EXECUTE FUNCTION validate_risk_assessment_dates();