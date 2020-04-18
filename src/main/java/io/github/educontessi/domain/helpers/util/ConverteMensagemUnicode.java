package io.github.educontessi.domain.helpers.util;

public class ConverteMensagemUnicode {

	public String converteMensagemUnicode(String mensagem) {
		String retorno = "";
		char[] chars = mensagem.toCharArray();

		for (char letra : chars) {
			retorno = retorno + geraCodigoUnicode(letra);
		}
		return retorno;
	}

	private String geraCodigoUnicode(char letra) {
		if (!verificaCaracterEspecial(String.valueOf(letra))) {
			return String.valueOf(letra);
		}

		String hexa = Integer.toHexString((int) letra);
		String prefix;

		if (hexa.length() == 1) {
			prefix = "\\u000";
		} else if (hexa.length() == 2) {
			prefix = "\\u00";
		} else if (hexa.length() == 3) {
			prefix = "\\u0";
		} else {
			prefix = "\\u";
		}

		return prefix + hexa;
	}

	private boolean verificaCaracterEspecial(String letra) {
		String caracteres = "á,à,â,ã,ä,Á,À,Â,Ã,Ä,é,è,ê,ê,É,È,Ê,Ë,í,ì,î,ï,Í,Ì,Î,Ï,ó,ò,ô,õ,ö,Ó,Ò,Ô,Õ,Ö,ú,ù,û,ü,Ú,Ù,Û,ç,Ç,ñ,Ñ,&,'";
		return caracteres.contains(letra);
	}
}
