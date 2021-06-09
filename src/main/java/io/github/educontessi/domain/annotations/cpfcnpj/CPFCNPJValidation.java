package io.github.educontessi.domain.annotations.cpfcnpj;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.InputMismatchException;

public class CPFCNPJValidation implements ConstraintValidator<CPFCNPJ, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean retorno = false;

		if (value == null || value.isEmpty()) {
			retorno = true;
		} else {
			value = value.replaceAll("[^0123456789]", "");
			if (value.length() == 11) {
				retorno = isCPFValid(value);
			} else if (value.length() == 14) {
				retorno = isCNPJValid(value);
			}
		}

		return retorno;
	}

	protected boolean isCPFValid(String cpf) {
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || (cpf.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48);

			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	protected boolean isCNPJValid(String cnpj) {
		if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") || cnpj.equals("22222222222222")
				|| cnpj.equals("33333333333333") || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
				|| cnpj.equals("66666666666666") || cnpj.equals("77777777777777") || cnpj.equals("88888888888888")
				|| cnpj.equals("99999999999999") || (cnpj.length() != 14))
			return (false);

		char dig13, dig14;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = 2;
			for (i = 11; i >= 0; i--) {
				num = (int) (cnpj.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig13 = '0';
			else
				dig13 = (char) ((11 - r) + 48);

			sm = 0;
			peso = 2;
			for (i = 12; i >= 0; i--) {
				num = (int) (cnpj.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig14 = '0';
			else
				dig14 = (char) ((11 - r) + 48);

			if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

}
