package com.firefly.core.contracts.interfaces.enums;

/**
 * Contract status enumeration matching the database enum contract_status_enum
 */
public enum ContractStatusEnum {
    DRAFT,
    PENDING_APPROVAL,
    ACTIVE,
    SUSPENDED,
    TERMINATED,
    EXPIRED,
    CANCELLED
}
