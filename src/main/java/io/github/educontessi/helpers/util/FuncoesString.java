package io.github.educontessi.helpers.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class FuncoesString {

	public static String adicionaMascara(TipoMascara mascara, Object value) {
		return adicionaMascara(mascara.getMascara(), value);
	}

	private static String adicionaMascara(String mascara, Object value) {
		MaskFormatter mask;
		try {
			mask = new MaskFormatter(mascara);
			mask.setValueContainsLiteralCharacters(false);
			value = value == null ? "" : value;
			return mask.valueToString(value);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String removeMascaraDeNumeros(String value) {
		if (value == null) {
			return value;
		}
		return value.replaceAll("[^0123456789]", "");
	}

}
