package com.firefly.core.contracts.interfaces.enums;

/**
 * Term validation type enumeration matching the database enum term_validation_type_enum
 */
public enum TermValidationTypeEnum {
    REQUIRED,
    MIN_LENGTH,
    MAX_LENGTH,
    MIN_VALUE,
    MAX_VALUE,
    REGEX_PATTERN,
    ENUM_VALUES,
    DATE_RANGE,
    CUSTOM_FUNCTION
}
