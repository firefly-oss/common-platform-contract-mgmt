package com.firefly.core.contracts.interfaces.enums;

/**
 * Event type enumeration matching the database enum event_type_enum
 */
public enum EventTypeEnum {
    CONTRACT_CREATED,
    CONTRACT_SIGNED,
    CONTRACT_ACTIVATED,
    CONTRACT_AMENDED,
    CONTRACT_RENEWED,
    CONTRACT_SUSPENDED,
    CONTRACT_TERMINATED,
    CONTRACT_EXPIRED,
    MILESTONE_REACHED,
    PAYMENT_DUE,
    PAYMENT_RECEIVED,
    BREACH_REPORTED,
    DISPUTE_RAISED,
    AUDIT_COMPLETED
}
