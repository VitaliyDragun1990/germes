package org.itsimulator.germes.app.infra.util;

import org.itsimulator.germes.app.infra.exception.flow.InvalidParameterException;

public class Checks {

	private Checks() {
	}
	
	/**
	 * Verifies that specified check passed and throws exception otherwise
	 * @param check specific check to verify
	 * @param message specific message to pass to possible exception
	 */
	public static void checkParam(boolean check, String message) throws InvalidParameterException {
		if (!check) {
			throw new InvalidParameterException(message);
		}
	}

}
