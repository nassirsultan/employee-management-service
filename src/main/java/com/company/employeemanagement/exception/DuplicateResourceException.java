package com.company.employee_management_service.exception;

public class DuplicateResourceException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateResourceException(String message) {
        super(message);
    }
}

