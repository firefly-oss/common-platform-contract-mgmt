ALTER TABLE contract_party DROP COLUMN role_in_contract;
DROP CAST (varchar AS role_in_contract);
ALTER TABLE contract_party ADD COLUMN role_in_contract BIGINT NOT NULL;