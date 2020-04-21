package io.github.educontessi.domain.service.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidatorExecutor {

	private final List<Validator> validators;

	public ValidatorExecutor() {
		this.validators = new ArrayList<>();
	}

	public ValidatorExecutor(List<Validator> validators) {
		this.validators = validators;
	}

	public void execute() {
		validators.forEach(v -> v.validate());
	}

	public void add(Validator validator) {
		this.validators.add(validator);
	}

	public void add(int index, Validator validator) {
		this.validators.add(index, validator);
	}

	public int size() {
		return validators.size();
	}

}
