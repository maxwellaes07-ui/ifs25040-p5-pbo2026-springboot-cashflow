package org.delcom.ifs25040springbootcashflow.domain.exception;

/** Validasi input bisnis gagal. */
public class ValidationException extends DomainException {
    public ValidationException(String message) {
        super(message);
    }
}
