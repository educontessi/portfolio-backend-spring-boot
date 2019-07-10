package io.github.educontessi.helpers.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.StringUtils;

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

	/**
	 * Formatador de nome de pessoa
	 * 
	 * @param nome = nome da pessoa
	 * @return nome formatado
	 */
	public static String formatarNomePessoa(String nome) {
		nome = nome.toLowerCase().trim();
		nome = nome.replaceAll("\\s+", " ");
		String[] palavas = nome.split(" ");

		StringBuilder builder = new StringBuilder();
		for (String palavra : palavas) {
			builder.append(verificaPreposicaoNome(palavra) ? palavra : StringUtils.capitalize(palavra)).append(" ");
		}
		return builder.toString().trim();
	}

	private static boolean verificaPreposicaoNome(String palavra) {
		String caracteres = "da|das|de|do|dos|e";
		return caracteres.contains(palavra);
	}

}
