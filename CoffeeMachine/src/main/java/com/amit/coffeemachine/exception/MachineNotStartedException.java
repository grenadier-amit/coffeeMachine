package com.amit.coffeemachine.exception;

public class MachineNotStartedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
//executer service not started for n parallel operations
	public MachineNotStartedException(String message) {
		super(message);
	}

}
