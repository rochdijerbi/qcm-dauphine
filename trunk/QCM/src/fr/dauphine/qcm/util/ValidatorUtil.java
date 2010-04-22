package fr.dauphine.qcm.util;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public final class ValidatorUtil {

	private static ValidatorFactory factory;

	static {
		factory = Validation.buildDefaultValidatorFactory();
	}

	private ValidatorUtil() {
	}

	public static Validator getValidator() {
		return factory.getValidator();
	}
}
