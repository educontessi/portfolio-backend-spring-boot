package io.github.educontessi.model;

import static org.junit.Assert.assertFalse;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste para entidade {@link Pais}
 * 
 * @author Eduardo Contessi
 *
 */
public class PaisTest {

	private Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void teste() {
		Pais pais = new Pais();
		pais.setNome(
				"a222222222222werwerewrwe222222222222222222222222222222331231232131231231231233213123123213123123123123123123s");

		Set<ConstraintViolation<Pais>> violations = validator.validate(pais);
		assertFalse(violations.isEmpty());
	}
}
