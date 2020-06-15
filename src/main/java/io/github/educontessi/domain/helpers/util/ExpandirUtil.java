package io.github.educontessi.domain.helpers.util;

public class ExpandirUtil {

	private static final String DELIMITER_REGEX = ",";
	private static final String ALL = "tudo";

	public static boolean contains(String campo, String expandir) {
		if (!nullOrEmpty(expandir)) {
			return expandir.contains(ALL) || expandir.contains(campo);
		}

		return false;
	}

	public static String extrairSubExpadir(String campoAtual, String expandir) {
		String subExpandir = "";
		if (!nullOrEmpty(expandir)) {
			if (expandir.contains(ALL)) {
				subExpandir = ALL;
			} else {
				String[] all = expandir.split(DELIMITER_REGEX);

				for (String e : all) {
					String aux = e.replace(campoAtual, "").trim();
					if (aux.length() > 0) {
						subExpandir += DELIMITER_REGEX + (aux.startsWith(".") ? aux.replace(".", "") : aux);
					}
				}
			}

		}

		return subExpandir.replaceFirst(DELIMITER_REGEX, "").trim();
	}

	protected static boolean nullOrEmpty(String expandir) {
		return expandir == null || expandir.isEmpty();
	}

	// ---------------------------------------------------------------------------
	public static void main(String[] args) {

		// String campo = "rua";
		// String expandir = "rua.cidade, rua.cidade.estado, rua.cidade.estado.pais";
		// System.out.println(extrairSubExpadir(campo, expandir));

		// String campo2 = "cidade";
		// String expandir2 = "cidade.estado, cidade.estado.pais";
		// System.out.println(extrairSubExpadir(campo2, expandir2));

		// String campo3 = "estado";
		// String expandir3 = "estado.pais";
		// System.out.println(extrairSubExpadir(campo3, expandir3));

		// String campo4 = "rua";
		// String expandir4 = "rua.cidade, rua.cidade.estado, rua.cidade.estado.pais,
		// pessoa.credito, pessoa.crediario";
		// System.out.println(extrairSubExpadir(campo4, expandir4));

		String campo5 = "estado";
		String expandir5 = "estado,estado.pais";
		System.out.println(extrairSubExpadir(campo5, expandir5));

	}
}
