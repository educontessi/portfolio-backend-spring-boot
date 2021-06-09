package io.github.educontessi.domain.helpers.util;

public class ExpandirUtil {

	private ExpandirUtil(){
		throw new IllegalStateException("Utility class");
	}

	private static final String DELIMITER_REGEX = ",";
	private static final String PONTO = ".";
	private static final String ALL = "tudo";

	public static boolean contains(String campo, String expandir) {
		if (!FuncoesString.nullOrEmpty(expandir)) {
			return expandir.contains(ALL) || expandir.contains(campo);
		}
		return false;
	}

    public static String extrairSubExpadir(String campoAtual, String expandir) {
        StringBuilder subExpandir = new StringBuilder();

        if (FuncoesString.nullOrEmpty(expandir)) {
            return FuncoesString.EMPTY;
        }

        if (expandir.contains(ALL)) {
            return ALL;
        }

        String[] all = expandir.split(DELIMITER_REGEX);
        for (String e : all) {
            String aux = e.replace(campoAtual, "").trim();
            if (aux.length() > 0) {
                subExpandir.append(DELIMITER_REGEX);
                subExpandir.append(getSubExpandir(aux));
            }
        }
        return subExpandir.toString().replaceFirst(DELIMITER_REGEX, "").trim();
    }

    private static String getSubExpandir(String aux) {
        if(aux.startsWith(PONTO)){
            return aux.replaceFirst("\\.", "");
        }
	    return aux;
    }

}
