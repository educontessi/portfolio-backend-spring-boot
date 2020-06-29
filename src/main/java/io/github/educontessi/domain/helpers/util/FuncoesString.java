package io.github.educontessi.domain.helpers.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.StringUtils;

/**
 * Classe para manipulação de String
 * 
 * @author Eduardo Possamai Contessi
 *
 */
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
	public static String formatarNome(String nome) {
		StringBuilder builder = new StringBuilder();
		if (nome != null) {
			try {
				nome = nome.toLowerCase().trim();
				nome = nome.replaceAll("\\s+", " ");
				String[] palavras = nome.split(" ");

				for (String palavra : palavras) {

					String[] palavrasDMudo = palavra.split("'"); // D'Oeste / D'Água
					if (palavrasDMudo.length == 1) {
						builder.append(verificaPreposicaoNome(palavra) ? palavra : StringUtils.capitalize(palavra))
								.append(" ");
					} else if (palavrasDMudo.length == 2) {
						builder.append(StringUtils.capitalize(palavrasDMudo[0])).append("'");
						builder.append(StringUtils.capitalize(palavrasDMudo[1]));
					} else {
						throw new Exception("Nome Inválido");
					}
				}
				return builder.toString().trim();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return nome;
	}

	private static boolean verificaPreposicaoNome(String palavra) {
		String caracteres = "da|das|de|do|dos|e";
		return caracteres.contains(palavra);
	}

}
