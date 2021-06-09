package io.github.educontessi.domain.helpers.util;

import io.github.educontessi.domain.enums.TipoPessoa;
import io.github.educontessi.domain.exception.negocio.NegocioException;
import org.apache.commons.lang3.StringUtils;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

/**
 * Classe para manipulação de String
 * 
 * @author Eduardo Possamai Contessi
 *
 */
public class FuncoesString {

	public static final String EMPTY = "";

	private FuncoesString(){
		throw new IllegalStateException("Utility class");
	}

	public static String adicionaMascara(TipoMascara mascara, Object value) {
		return adicionaMascara(mascara.getMascara(), value);
	}

	public static String adicionaMascara(TipoPessoa tipoPessoa, Object value) {
		return adicionaMascara(TipoMascara.getTipoMascara(tipoPessoa), value);
	}

	private static String adicionaMascara(String mascara, Object value) {
		MaskFormatter mask;
		try {
			mask = new MaskFormatter(mascara);
			mask.setValueContainsLiteralCharacters(false);
			value = value == null ? "" : value;
			return mask.valueToString(value);
		} catch (ParseException e) {
			throw new NegocioException(e.getMessage());
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
						throw new NegocioException("Nome Inválido");
					}
				}
				return builder.toString().trim();
			} catch (Exception e) {
				throw new NegocioException(e.getMessage());
			}
		}
		return nome;
	}

	/**
	 * Formatador de descrição
	 * 
	 * @param descricao = descrição
	 * @return descricao formatada
	 */
	public static String formatarDescricao(String descricao) {
		if (descricao != null) {
			try {
				descricao = descricao.trim();
				descricao = descricao.replaceAll("\\s+", " ");
				return descricao;
			} catch (Exception e) {
				throw new NegocioException(e.getMessage());
			}
		}
		return descricao;
	}

	public static boolean nullOrEmpty(String expandir) {
		return expandir == null || expandir.isEmpty();
	}



	private static boolean verificaPreposicaoNome(String palavra) {
		String caracteres = "da|das|de|do|dos|e";
		return caracteres.contains(palavra);
	}



}
